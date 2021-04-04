package com.read.kotlinlib.coroutine

import android.widget.ImageView
import com.nostra13.universalimageloader.core.ImageLoader
import com.read.kotlinlib.coroutine.CoroutineActivity
import com.seniorlibs.baselib.utils.LogUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/3/15
 * Mender:
 * Modify:
 * Description: 测试 Coroutine
 */
class TestCoroutine {

    /**
     * 协程切换线程获取图片
     *
     * @param view
     */
    fun testImage(ivImage: ImageView) {
        LogUtils.d(CoroutineActivity.TAG, "testImage 开始： ${Thread.currentThread().name}")

        // 在主线程开启协程
        val scope = CoroutineScope(Dispatchers.Default)
        // 1.👈 在 UI 线程开始
        scope.launch(Dispatchers.Main) {
            // 2.👈 切换到 IO 线程，并在执行完成后切回 UI 线程
            val url = getImageUrl()  // 挂起函数的调用处称为"挂起点"
            val url1 = getImageUrl1()
            // 4.👈 回到 UI 线程更新 UI
            ImageLoader.getInstance().displayImage(url, ivImage)
        }

        // 不阻塞主线程
        LogUtils.d(CoroutineActivity.TAG, "testImage 结束： ${Thread.currentThread().name}")
    }

    /**
     * 挂起函数：以 suspend 修饰的函数；
     * 挂起函数只能在其他挂起函数或协程中调用；
     *
     * @return
     */
    suspend fun getImageUrl(): String {
        var url = ""
        withContext(Dispatchers.IO) {
            LogUtils.d(CoroutineActivity.TAG, "testImage 切到子线程： ${Thread.currentThread().name}")
            // 3.👈 将会运行在 IO 线程
            url = "https://mmbiz.qpic.cn/mmbiz_jpg/AcTPSZQQ6D0TtiaYoQzElnygYvTwTnQJDEj6fiaO9GbTR0FVzYicl3OQQ8FxdtUHY59IjetUjkkcCZCxNjLdAteVQ/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1"
            Thread.sleep(4000)
            LogUtils.d(CoroutineActivity.TAG, "testImage 子线程中获取图片： ${Thread.currentThread().name}")
        }
        return url
    }

    /**
     * 16:28:06.228 :: testImage 开始： main
     * 16:28:06.228 :: testImage 结束： main
     * 16:28:06.237 :: testImage 切到子线程： DefaultDispatcher-worker-6
     * 16:28:10.237 :: testImage 子线程中获取图片： DefaultDispatcher-worker-6
     */


    suspend fun getImageUrl1(): String {
        var url = ""

        return url
    }
}