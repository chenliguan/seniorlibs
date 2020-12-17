package com.seniorlibs.thread.atomic

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2019/9/1.
 * Mender:
 * Modify:
 * Description: 测试原子性Integer
 */
object AtomicLongFieldUpdaterTest {

    private const val TAG = "AtomicLongFieldUpdaterTest"

    var counter: Counter? = Counter()
    var compactCounter: CompactCounter? = CompactCounter()

    fun mainTest() {
        val r = AtomicRunnable()
        val t1 = Thread(r)
        val t2 = Thread(r)
        t1.start()
        t2.start()
        t1.join()
        t2.join()
        println(TAG + " 普通变量的结果：" + counter?.get())
        println(TAG + " 升级后的结果：" + compactCounter?.get())
    }

    class AtomicRunnable : Runnable {
        override fun run() {
            for (i in 0..10) {
                counter?.increase()
                compactCounter?.increase()
            }
        }
    }
}