package com.seniorlibs.thirdlib.okhttp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.seniorlibs.baselib.threadpool.ThreadPoolManager;
import com.seniorlibs.baselib.utils.JsonUtils;
import com.seniorlibs.baselib.utils.LogUtils;
import com.seniorlibs.thirdlib.R;
import com.seniorlibs.thirdlib.http.HttpCommonInterceptor;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

/**
 * Author: 陈李冠
 * Version: 1.0.0
 * Date: 2020/6/21
 * Mender:
 * Modify:
 * Description: OkHttp测试
 *              聚合数据api：https://www.juhe.cn/docs/api/id/46
 */
public class OkHttpActivity extends AppCompatActivity {

    private static final String TAG = "ThirdLib + OkHttpActivity";

    private static final String APPKEY = "8fac966b379367b0e6f0527d634324ee";

    private OkHttpClient mOkHttpClient;

    public static void actionStart(Context context) {
        if (context == null) {
            return;
        }

        Intent intent = new Intent(context, OkHttpActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        // 1、创建okHttpClient对象
        mOkHttpClient = getOkHttpClient();
    }

    private static synchronized OkHttpClient getOkHttpClient() {
        File httpCacheDirectory = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "OkHttpCache");

        return new OkHttpClient
                .Builder()
                // 公共参数拦截器
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        Request.Builder builder = chain.request().newBuilder()
                                .header("userId", "11249")
                                .header("sessionId", "155056366467311249");
                        Request build = builder.build();
                        return chain.proceed(build);
                    }
                })
                // 缓存响应：设置读写的缓存目录，和缓存大小的限制
                .cache(new Cache(httpCacheDirectory, 10 * 1024 * 1024))  // 10 * 1024 * 1024
                // 缓存拦截器
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Response response = chain.proceed(request);
                        response.newBuilder()
                                .removeHeader("Pragma")
                                // 确定响应缓存多长时间 read from cache for 1 minute
                                .header("Cache-Control", "public, max-age=" + 60 * 60)
                                .build();
                        return response;
                    }
                })
                // 连接超时
                .connectTimeout(3000, TimeUnit.MILLISECONDS)
                // 读取超时
                .readTimeout(3000, TimeUnit.MILLISECONDS)
                // 写入超时
                .writeTimeout(3000, TimeUnit.MILLISECONDS)
                .build();
    }


    /**
     * 异步get请求
     *
     * @param view
     */
    public void getEnqueue(View view) {
//        // 1、创建okHttpClient对象
//        OkHttpClient mOkHttpClient = new OkHttpClient();
        // 2、创建一个Request对象
        final Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .tag(this)
                .build();
        // 3、通过request的对象去构造一个Call对象，将你的请求封装成了任务，有execute()和cancel()等方法
        Call call = mOkHttpClient.newCall(request);
        // 4、调用的是call.enqueue以异步的方式去执行请求，将call加入调度队列
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                LogUtils.e(TAG, "onFailure：" + e.toString());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull final Response response) throws IOException {
                // 5、Response
                // 通过response.body().string()获取返回的字符串；
                // 调用response.body().bytes()获得返回的二进制字节数组；
                // 调用response.body().byteStream()获取返回的inputStream
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                LogUtils.d(TAG, "response.request().url()：" + response.request().url());
                // onResponse执行的线程并不是UI线程
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            LogUtils.d(TAG, "response：" + response.body().string());
                            LogUtils.d(TAG, "cacheResponse：" + response.cacheResponse());
                            LogUtils.d(TAG, "networkResponse：" + response.networkResponse());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    /**
     * 同步get请求
     *
     * @param view
     */
    public void getExecute(View view) {
        ThreadPoolManager.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                // 2、创建一个Request对象
                final Request request = new Request.Builder()
                        .url("https://publicobject.com/helloworld.txt")
                        .tag(this)
                        .build();
                // 3、通过request的对象去构造一个Call对象，将你的请求封装成了任务，有execute()和cancel()等方法
                Call call = mOkHttpClient.newCall(request);
                try {
                    // 4、调用call.execute()以阻塞的方式去执行请求 Response response = client.newCall(request).execute();
                    Response response = call.execute();
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                    // 5、Response
                    LogUtils.d(TAG, "response：" + response.body().string());
                    LogUtils.d(TAG, "cacheResponse：" + response.cacheResponse());
                    LogUtils.d(TAG, "networkResponse：" + response.networkResponse());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 添加请求头
     *
     * @param view
     */
    public void addHeader(View view) {
        final Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .tag(this)
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                LogUtils.e(TAG, "onFailure：" + e.toString());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                Headers responseHeaders = response.headers();
                for (int i = 0; i < responseHeaders.size(); i++) {
                    LogUtils.d(TAG, "响应头" + i + " : " + responseHeaders.name(i) + " =  " + responseHeaders.value(i));
                }
                Headers requestHeaders = response.request().headers();
                for (int i = 0; i < requestHeaders.size(); i++) {
                    LogUtils.d(TAG, "请求头" + i + " : " + requestHeaders.name(i) + " =  " + requestHeaders.value(i));
                }
                LogUtils.d(TAG, "response：" + response.body().string());
            }
        });
    }


    /**
     * Post方式提交表单
     *
     * Content-Type实体头部用于用来指定资源MIME类型 media type。默认表单类型提交：application/x-www-form-urlencoded
     *
     * @param view
     */
    public void postForm(View view) {
        Map<String, String> params = new HashMap<>();
        params.put("parentid", "10001");
        params.put("key", APPKEY);
        params.put("dtype", "json");

        FormBody.Builder formBuilder = new FormBody.Builder();
        if (params.size() > 0) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (entry == null || TextUtils.isEmpty(entry.getKey())) {
                    continue;
                }
                String value = entry.getValue();
                if (value == null) {
                    value = "";
                }
                formBuilder.add(entry.getKey(), value);
            }
        }

        Request request = new Request.Builder()
                .url("http://apis.juhe.cn/cook/category")
                .post(formBuilder.build())
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                LogUtils.e(TAG, "onFailure：" + e.toString());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                LogUtils.d(TAG, "response：" + response.body().string());
            }
        });
    }

    /**
     * Post方式提交String
     *
     * 告诉服务端body是序列化后的JSON字符串，application/json
     *
     * @param view
     */
    public void postString(View view) {
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        String json = "{\"api_version\":\"v.2.0\",\"device\":{\"carrier\":2,\"connection_type\":1,\"device_id\":\"864412033998636\",\"geo\":{\"lat\":39.913951,\"lon\":116.403923},\"ip\":\"172.0.0.0\",\"os\":2,\"os_v\":\"6.0.1\",\"ua\":\"Mozilla/5.0 (Linux; Android 6.0.1; OPPO R9sk Build/MMB29M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/76.0.3809.132 Mobile Safari/537.36\"}}";
        RequestBody body = RequestBody.create(mediaType, json);

        Request request = new Request.Builder()
                .url("https://adx.58.com/api/v2/rtb")
                .post(body)
                .tag(this)
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                LogUtils.e(TAG, "onFailure：" + e.toString());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                LogUtils.d(TAG, "response：" + response.body().string());
            }
        });
    }

    /**
     * Post方式提交文件
     *
     * 表单上传文件类型multipart/form-data专，用有效的传输文件。
     * 为什么不用默认的application/x-www-form-urlencoded呢？因为此类型不适合用于传输大型二进制数据或者包含非ASCII字符的数据
     *
     * @param view
     */
    public void postFile(View view) {
        MediaType mediaType = MediaType.parse("multipart/form-data");
        File file = new File("/storage/emulated/0/vlog.txt");
        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(mediaType, file))
                .tag(this)
                .build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                LogUtils.e(TAG, "onFailure：" + e.toString());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                LogUtils.d(TAG, "response：" + response.body().string());
            }
        });
    }

    /**
     * 取消当前context的所有请求
     *
     * @param view
     */
    public void cancel(View view) {
        if (mOkHttpClient != null) {
            for (Call call : mOkHttpClient.dispatcher().queuedCalls()) {
                if (call.request().tag().equals(this)) {
                    // call.cancel()可以立即停止掉一个正在执行的call。如果一个线程正在写请求或者读响应，将会引发IOException，使用这个api可以节约网络资源
                    call.cancel();
                }
            }
            for (Call call : mOkHttpClient.dispatcher().runningCalls()) {
                if (call.request().tag().equals(this)) {
                    call.cancel();
                }
            }
        }
    }
}
