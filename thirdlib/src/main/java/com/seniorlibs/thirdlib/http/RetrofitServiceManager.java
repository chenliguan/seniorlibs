package com.seniorlibs.thirdlib.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceManager {

    /**
     * 超时时间 5s
     */
    private static final int DEFAULT_TIME_OUT = 5;
    private static final int DEFAULT_READ_TIME_OUT = 10;
    private Retrofit mRetrofit;

    private static class SingletonHolder{
        private static final RetrofitServiceManager INSTANCE = new RetrofitServiceManager();
    }

    /**
     * 获取RetrofitServiceManager
     *
     * @return
     */
    public static RetrofitServiceManager getInstance(){
        return SingletonHolder.INSTANCE;
    }


    private RetrofitServiceManager(){
        // 创建 OKHttpClient
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // 连接-写操作-读操作超时时间
        httpClient.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        httpClient.writeTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);
        httpClient.readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);
        // 添加公共参数拦截器
        HttpCommonInterceptor commonInterceptor = new HttpCommonInterceptor.Builder()
                .addHeaderParams("paltform","android")
                .addHeaderParams("userToken","1234343434dfdfd3434")
                .addHeaderParams("userId","123445")
//                .addHeaderParams("Accept", "application/json")
//                .addHeaderParams("Accept", "*/*")
//                .addHeaderParams("User-Agent", "Apache-HttpClient/UNAVAILABLE (java 1.4)")
//                .addHeaderParams("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
//                .addHeaderParams("Connection", "Keep-Alive")
//                .addHeaderParams("Charset", "UTF-8")
                .build();
        httpClient.addInterceptor(commonInterceptor);

        // 创建Retrofit
        mRetrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiConfig.BASE_URL)
                .build();
    }

    /**
     * 获取对应的Service
     *
     * @param service Service 的 class
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> service){
        return mRetrofit.create(service);
    }
}
