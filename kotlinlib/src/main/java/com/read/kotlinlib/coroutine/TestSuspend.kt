package com.read.kotlinlib.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

/*

-> BuildersKt.withContext(context, block) -> block.startCoroutineCancellable(coroutine, Continuation)
-> resumeCancellableWith(Result.success(Unit)) -> 切换线程执行，回调结果 -> resumeWith(result)
-> BaseContinuationImpl.resumeWith(result) ->invokeSuspend(result) { result = $result; }

 */

suspend fun getUserInfo(): String {
    var url = ""
    withContext(Dispatchers.IO) {
        delay(1000L)
        url = "result + getUserInfo"
    }
    return url
}

//挂起函数
// ↓
suspend fun getFriendList(user: String): String {
    withContext(Dispatchers.Unconfined) {
        delay(1000L)
    }
    return "result + getFriendList"
}

//挂起函数
// ↓
suspend fun getFeedList(list: String): String {
    withContext(Dispatchers.IO) {
        delay(1000L)
    }
    return "result + getFeedList"
}

//挂起函数
// ↓
suspend fun getEndList(list: String): String {
    withContext(Dispatchers.IO) {
        delay(1000L)
    }
    return "result + getEndList"
}

suspend fun testCoroutine() {
    log("start")
    val user = getUserInfo()
    log("user $user")
    val friendList = getFriendList(user)
    log("friendList $friendList")
    val feedList = getFeedList(friendList)
    log("feedList $feedList")
    val endList = getEndList(feedList)
    log("endList $endList")
}

suspend fun main() {
    testCoroutine()
}

fun log(msg: Any) {
    println("${Thread.currentThread().name} msg=$msg")
}