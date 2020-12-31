package com.seniorlibs.thread.threadpool;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.seniorlibs.baselib.utils.LogUtils;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/12/31.
 * Mender:
 * Modify:
 * Description: ForkJoinPool
 */
class ForkJoinPoolTest {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void main() {
//        for (i in 2 until size) {
//            dp[i] = dp[i - 1] + dp[i - 2]
//        }

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        for (int i = 0; i < 10; i++) {
            ForkJoinTask task = forkJoinPool.submit(new Fibonacci(i));
            try {
                LogUtils.d(ThreadPoolManagerActivity.TAG, "ForkJoinPool：" + task.get());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    static class Fibonacci extends RecursiveTask<Integer> {

        int n;

        public Fibonacci(int n) {
            this.n = n;
        }

        @Override
        public Integer compute() {
            if (n <= 1) {
                return n;
            }

            // 当 n>1 就创建递归任务，也就是 f1 和 f2，用 fork() 方法分裂任务并分别执行，
            Fibonacci f1 = new Fibonacci(n - 1);
            f1.fork();
            Fibonacci f2 = new Fibonacci(n - 2);
            f2.fork();

            // 最后在 return 的时候，使用 join() 方法把结果汇总
            return f1.join() + f2.join();
        }
    }
}
