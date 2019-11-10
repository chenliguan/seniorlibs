package com.seniorlibs.binder.aidl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;

/**
 * 服务端
 */
public class BookManagerService extends Service {

    private static final String TAG = "TAG-BookManagerService";

    /**
     * C1、加入权限验证功能，验证通过后才允许链接
     */
    private static final String PERMISSION_CHECK = "com.seniorlibs.binder.permission.ACCESS_BOOK_SERVICE";

    private AtomicBoolean mIsServiceDestory;
    /**
     * AIDL方法是在服务端的Binder线程池中执行的，因此当多个客户端同时连接的时候，会存在多个线程同时访问的情形，所以我们要在AIDL方法中处理线程同步
     * 直接使用CopyonWriteArayList来进行自动的线程同步
     */
    private CopyOnWriteArrayList<Book> mBookList;
    // private CopyOnWriteArrayList<IOnNewBookArrivedListener> mListenerList;
    /**
     * D1、RemoteCallbackList是系统专门用来删除listener的接口，RemoteCallbackList是一个泛型，支持管理任意的AIDL接口
     * 当客户端解注册的时候，我们只要遍历服务端所有的listener，找到那个和解注册listener具有相同Binder对象的服务端listener并把它删掉
     */
    private RemoteCallbackList<IOnNewBookArrivedListener> mListenerList ;

    @Override
    public void onCreate() {
        super.onCreate();

        initData();
    }

    private void initData() {
        mIsServiceDestory = new AtomicBoolean(false);
        mBookList = new CopyOnWriteArrayList<>();
//        mListenerList = new CopyOnWriteArrayList<IOnNewBookArrivedListener>();
        mListenerList = new RemoteCallbackList<>();

        mBookList.add(new Book(1, "Android"));
        mBookList.add(new Book(2, "Ios"));

        // B1、服务端开启线程
        new Thread(new ServiceWorker()).start();
    }

    /**
     * A4、返回服务端的Binder对象
     *
     * @param intent
     * @return
     */
    @Override
    public IBinder onBind(Intent intent) {
        // C3、权限验证方法一：没有此权限，onBind()直接返回null
        int check = checkCallingOrSelfPermission(PERMISSION_CHECK);
        Log.d(TAG, "权限验证方法一：onbind check=" + check);
        if (check == PackageManager.PERMISSION_DENIED) {
            return null;
        }

        return mBinder;
    }

    /**
     * A3、服务端实现AIDL接口方法
     *
     * 注意：由于客户端的onServiceConnected和onServiceDisconnected方法都运行在UI线程中，所以不可以在它们里面直接调用服务端的耗时方法，
     * 另外，由于服务端的方法本身就运行在服务端的Binder线程池中，所以服务端方法本身就可以执行大量耗时操作，这时切记不要在服务端方法中开线程执行异步任务。
     */
    private Binder mBinder = new IBookManager.Stub() {

        @Override
        public List<Book> getBookList() {
            SystemClock.sleep(5000);
            return mBookList;
        }

        @Override
        public void addBook(Book book) {
            mBookList.add(book);
        }

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            // C4、权限验证方法二的方式1：没有此权限onTransact()直接返回false
            int check = checkCallingOrSelfPermission(PERMISSION_CHECK);
            Log.d(TAG, "check=" + check);
            if (check == PackageManager.PERMISSION_DENIED) {
                return false;
            }

            // C4、权限验证方法二的方式2：采用UID和PID来验证包名，没有此权限onTransact()直接返回false
            String packageName = null;
            String[] packages = getPackageManager().getPackagesForUid(getCallingUid());
            if (packages != null && packages.length > 0) {
                packageName = packages[0];
            }
            if (packageName != null && !packageName.startsWith("com.seniorlibs.binder")) {
                return false;
            }

            return super.onTransact(code, data, reply, flags);
        }

        /**
         * CopyOnWriteArrayList<IOnNewBookArrivedListener>
         */
//        @Override
//        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
//            if(!mListenerList.contains(listener)){
//                mListenerList.add(listener);
//            } else {
//                Log.i(TAG,"already exists");
//            }
//            Log.i(TAG,"registerListener size:" + mListenerList.size());
//        }
//
//        @Override
//        public void unregisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
//            if(mListenerList.contains(listener)){
//                mListenerList.remove(listener);
//                Log.i(TAG,"remove listener");
//            } else {
//                Log.i(TAG,"can not remove listener");
//            }
//            Log.i(TAG,"unregisterListener size:" + mListenerList.size());
//        }

        /**
         * RemoteCallbackList<IOnNewBookArrivedListener>
         */
        @Override
        public void registerListener(IOnNewBookArrivedListener listener) {
            // D2、注册监听器IOnNewBookArrivedListener
            mListenerList.register(listener);

            final int N = mListenerList.beginBroadcast();
            mListenerList.finishBroadcast();
            Log.d(TAG, "registerListener, current size:" + N);
        }

        @Override
        public void unregisterListener(IOnNewBookArrivedListener listener) {
            // D2、解注册监听器IOnNewBookArrivedListener
            boolean success = mListenerList.unregister(listener);
            Log.d(TAG, success ? "unregister success." : "not found, can not unregister.");

            // D4、RemoteCallbackList并不是List，不能像操作List操作它，必须beginBroadcast()和finishBroadcast()配对使用
            final int N = mListenerList.beginBroadcast();
            mListenerList.finishBroadcast();
            Log.d(TAG, "unregisterListener, current size:" + N);
        }
    };

    /**
     * B2、开启线程，每隔20s向感兴趣的用户提醒
     */
    private class ServiceWorker implements Runnable {
        @Override
        public void run() {
            while (!mIsServiceDestory.get()) {
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                int bookId = mBookList.size() + 1;
                setNewBookArrived(new Book(bookId, "新书：" + bookId));
            }
        }
    }

    private void setNewBookArrived(Book book) {
        mBookList.add(book);
        final int N = mListenerList.beginBroadcast();
        for (int i = 0; i < N; i++) {
            IOnNewBookArrivedListener listener = mListenerList.getBroadcastItem(i);
            if (listener != null) {
                try {
                    // B4、当有新书的时候，服务端会回调客户端的IOnNewBookArrivedListener对象中的onNewBookArrived方法
                    listener.onNewBookArrived(book);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
        mListenerList.finishBroadcast();
    }

    @Override
    public void onDestroy() {
        mIsServiceDestory.set(true);
        super.onDestroy();
    }
}
