package com.seniorlibs.thirdlib.glide

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.seniorlibs.thirdlib.okhttp.OkHttpEventListener
import okhttp3.OkHttpClient
import java.io.InputStream

/**
 * Author: 陈李冠
 * Time: 2021/5/5
 * Description: 自定义 GlideModule，重写 applyOptions() 和 registerComponents() 方法，这两个方法分别就是用来更改 Glide 和配置以及替换 Glide 组件的。
 */
@GlideModule
class MyGlideModule : AppGlideModule() {

    /**
     * 更改 Glide 和配置
     *
     * @param context
     * @param builder
     */
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        // if (diskCacheFactory == null) {
        //      diskCacheFactory = new InternalCacheDiskCacheFactory(context);
        // }
        builder.setDiskCache(ExternalCacheDiskCacheFactory(context))
    }

    /**
     * 替换 Glide 组件
     *
     * @param context
     * @param glide
     * @param registry
     */
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        // 创建 httpClient
        val client = OkHttpClient.Builder()
            .eventListenerFactory(OkHttpEventListener.FACTORY)
            .build()

        // 替换 ModelLoader 模型加载器，用于使用 OkHttp 通过 http/https 获取媒体
        // register(GlideUrl::class.java, InputStream::class.java, Factory())
        registry.replace(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory(client))
    }
}