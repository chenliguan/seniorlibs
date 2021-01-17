package com.seniorlibs.thirdlib.retrofit;

import com.seniorlibs.thirdlib.http.ObjectLoader;
import com.seniorlibs.thirdlib.http.RetrofitServiceManager;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/1/3
 * Mender:
 * Modify:
 * Description: 干货集中营api https://gank.io/api
 */
public class GankLoader extends ObjectLoader {

    private GankService mGankService;

    public GankLoader() {
        mGankService = RetrofitServiceManager.getInstance().create(GankService.class);
    }

    /**
     * 获取干货列表
     *
     * @return
     */
    public Observable<List<GankEntry>> getGankList() {
        return observe(mGankService.getGank(100,1))
                .map(new Function<GankResp, List<GankEntry>>() {
                    @Override
                    public List<GankEntry> apply(GankResp gankResp) {
                        return gankResp.results;
                    }
                });
    }

    public interface GankService {

        /**
         * http://gank.io/api/data/福利/100/1
         * @param url
         * @return
         */
        @GET
        Observable<GankResp> getGank(@Url String url);

        @GET("data/福利/100/1")
        Observable<GankResp> getGank();

        @GET("data/福利/{count}/{page}")
        Observable<GankResp> getGank(@Path("count") int count, @Path("page") int page);

//        /**
//         * http://gank.io/api/data/福利?count=100&page=1
//         */
//        @GET("data/福利")
//        Observable<GankResp> getGank(@Query("count") int count, @Query("page") int page);

//        /**
//         * 使用POST方式时注意2点，1，必须加上 @FormUrlEncoded标签，否则会抛异常。2，使用POST方式时，必须要有参数，否则会抛异常,
//         */
//        @FormUrlEncoded
//        @POST("data/福利")
//        Observable<GankResp> getGank(@Field("count") int count, @Field("page") int page);
    }
}
