package com.seniorlibs.thread.asynctask;

import android.os.AsyncTask;

import com.seniorlibs.baselib.utils.LogUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/7/6.
 * Mender:
 * Modify:
 * Description: 测试AsyncTask
 */
public class AsyncTaskTest {

    private static final String TAG = "AsyncTaskTest";

    /**
     * execute()
     *
     * Android 2.3.3上执行：从下面Log可以看出，5个AsyncTask的结束时间是一样的，是并行执行
     *
     * Android 4.1.1上执行：从下面Log可以看出，5个AsyncTask时间间隔为3s，是串行执行的
     */
    public static void testExecute() {
        new MyAsyncTask("AsyncTask#1").execute("");
        new MyAsyncTask("AsyncTask#2").execute("");
        new MyAsyncTask("AsyncTask#3").execute("");
        new MyAsyncTask("AsyncTask#4").execute("");
        new MyAsyncTask("AsyncTask#5").execute("");
    }

    /**
     * executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"")执行
     *
     * Android 4.1.1上执行：从下面Log可以看出，5个AsyncTask的结束时间是一样的，是并行执行
     *
     */
    public static void testExecuteOnExecutor() {
        new MyAsyncTask("AsyncTask#1").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"");
        new MyAsyncTask("AsyncTask#2").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"");
        new MyAsyncTask("AsyncTask#3").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"");
        new MyAsyncTask("AsyncTask#4").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"");
        new MyAsyncTask("AsyncTask#5").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"");
    }

    /**
     * MyAsyncTask
     */
    private static class MyAsyncTask extends AsyncTask<String, Integer, String> {
        private String mName = "AsyncTask";

        public MyAsyncTask(String name) {
            super();
            mName = name;
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return mName;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            LogUtils.e(TAG, result + "execute finish at " + df.format(new Date()));
        }
    }
}
