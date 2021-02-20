package com.read.kotlinlib.jvm;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2021/2/18.
 * Mender:
 * Modify:
 * Description: 测试 MinorGC
 */
public class MinorGCTest {

    private static String TAG = "GCRoot";

    private static final int _01MB = 1024;
    private static final int _1MB = 1024 * 1024;

    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[1 * _1MB];  // 512 * _01 MB
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[1 * _1MB];  // 3 MB
    }

    public static void main(String[] args) {
        testAllocation();
    }

//    java -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 MinorGCTest

//    没分配任何 allocation 时，Eden 区内存占用是 1312K
//    Heap
//    PSYoungGen      total 9216K, used 1312K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
//    eden space 8192K, 16% used [0x00000000ff600000,0x00000000ff7480a0,0x00000000ffe00000)
//    from space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
//    to   space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)
//    ParOldGen       total 10240K, used 0K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
//    object space 10240K, 0% used [0x00000000fec00000,0x00000000fec00000,0x00000000ff600000)
//    Metaspace       used 2628K, capacity 4486K, committed 4864K, reserved 1056768K
//    class space    used 281K, capacity 386K, committed 512K, reserved 1048576K

//    分配 allocation1 时，Eden 区内存占用是 3360K
//    allocation1 = new byte[2 * _1MB];
//    Heap
//    PSYoungGen      total 9216K, used 3360K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
//    eden space 8192K, 41% used [0x00000000ff600000,0x00000000ff9480b0,0x00000000ffe00000)
//    from space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
//    to   space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)
//    ParOldGen       total 10240K, used 0K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
//    object space 10240K, 0% used [0x00000000fec00000,0x00000000fec00000,0x00000000ff600000)
//    Metaspace       used 2628K, capacity 4486K, committed 4864K, reserved 1056768K
//    class space    used 281K, capacity 386K, committed 512K, reserved 1048576K

//    分配 allocation1/2/3 时，Eden 区内存占用是 7456K
//    allocation1 = new byte[2 * _1MB];
//    allocation2 = new byte[2 * _1MB];
//    allocation3 = new byte[2 * _1MB];
//    Heap
//    PSYoungGen      total 9216K, used 7456K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
//    eden space 8192K, 91% used [0x00000000ff600000,0x00000000ffd480d0,0x00000000ffe00000)
//    from space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
//    to   space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)
//    ParOldGen       total 10240K, used 0K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
//    object space 10240K, 0% used [0x00000000fec00000,0x00000000fec00000,0x00000000ff600000)
//    Metaspace       used 2628K, capacity 4486K, committed 4864K, reserved 1056768K
//    class space    used 281K, capacity 386K, committed 512K, reserved 1048576K

//    分配 allocation1/2/3/4 时，Eden 区内存占用是 2M
//    allocation1 = new byte[2 * _1MB];
//    allocation2 = new byte[2 * _1MB];
//    allocation3 = new byte[2 * _1MB];
//    allocation4 = new byte[2 * _1MB];
//    Heap
//    PSYoungGen      total 9216K, used 2130K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
//    eden space 8192K, 26% used [0x00000000ff600000,0x00000000ff814930,0x00000000ffe00000)
//    from space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)
//    to   space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
//    ParOldGen       total 10240K, used 6773K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
//    object space 10240K, 66% used [0x00000000fec00000,0x00000000ff29d748,0x00000000ff600000)
//    Metaspace       used 2629K, capacity 4486K, committed 4864K, reserved 1056768K
//    class space    used 281K, capacity 386K, committed 512K, reserved 1048576K

//    这是因为在给 a4 分配内存之前，Eden 区已经被占用 6M + 1.4M(初始)，已经无法再分配出 2M 来存储 a4 对象。
//    因此会执行一次 Minor GC，也清理了初始内存。
//    并尝试将 Eden 区存活的 a1、a2、a3 和 S1 区(初始空)复制到 S1 区。但是 S1 区只有 1M 空间，没有办法存储 a1、a2、a3 任意一个对象。
//    因此 a1、a2、a3 将被转移到老年代，最后将新分配的 a4 保存在 Eden 区。所以最终结果是：Eden 区占用 2M(a4)，老年代占用 6M(a1、a2、a3)。

//    总结：S0 的空间如果不足以装下所有 Eden + S1 中存活下来的对象，则直接转移到老年代，
//    保证一方（Eden + S1）中没有任何剩余对象。新分配的内存装在 Eden 区（前提是 Eden 足够空间，否者新分配的内存一起转移到老年代）。



//    没分配任何 allocation 时，Eden 区内存占用是 1312K
//    Heap
//    PSYoungGen      total 9216K, used 1312K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
//    eden space 8192K, 16% used [0x00000000ff600000,0x00000000ff7480a0,0x00000000ffe00000)
//    from space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
//    to   space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)
//    ParOldGen       total 10240K, used 0K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
//    object space 10240K, 0% used [0x00000000fec00000,0x00000000fec00000,0x00000000ff600000)
//    Metaspace       used 2628K, capacity 4486K, committed 4864K, reserved 1056768K
//    class space    used 281K, capacity 386K, committed 512K, reserved 1048576K


//    分配 allocation1/2/3/4 时，Eden 区内存占用是 3M，S0 区是 798K，Old 区是 4M。
//    allocation1 = new byte[0 * _01MB];
//    allocation2 = new byte[2 * _1MB];
//    allocation3 = new byte[2 * _1MB];
//    allocation4 = new byte[3 * _1MB];
//[GC (Allocation Failure) [PSYoungGen: 5243K->808K(9216K)] 5243K->4912K(19456K), 0.0036631 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
//    Heap
//    PSYoungGen      total 9216K, used 3962K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
//    eden space 8192K, 38% used [0x00000000ff600000,0x00000000ff914930,0x00000000ffe00000)
//    from space 1024K, 78% used [0x00000000ffe00000,0x00000000ffeca050,0x00000000fff00000)
//    to   space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
//    ParOldGen       total 10240K, used 4104K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
//    object space 10240K, 40% used [0x00000000fec00000,0x00000000ff002020,0x00000000ff600000)
//    Metaspace       used 2628K, capacity 4486K, committed 4864K, reserved 1056768K
//    class space    used 281K, capacity 386K, committed 512K, reserved 1048576K


//    分配 allocation1/2/3/4 时，Eden 区内存占用是 3M，S0 区是 998K（增加200k），Old 区是 4M。
//    allocation1 = new byte[200 * _01MB];
//    allocation2 = new byte[2 * _1MB];
//    allocation3 = new byte[2 * _1MB];
//    allocation4 = new byte[3 * _1MB];
//[GC (Allocation Failure) [PSYoungGen: 5444K->952K(9216K)] 5444K->5056K(19456K), 0.0037164 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
//    Heap
//    PSYoungGen      total 9216K, used 4106K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
//    eden space 8192K, 38% used [0x00000000ff600000,0x00000000ff914930,0x00000000ffe00000)
//    from space 1024K, 97% used [0x00000000ffe00000,0x00000000ffeee050,0x00000000fff00000)
//    to   space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
//    ParOldGen       total 10240K, used 4104K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
//    object space 10240K, 40% used [0x00000000fec00000,0x00000000ff002020,0x00000000ff600000)
//    Metaspace       used 2629K, capacity 4486K, committed 4864K, reserved 1056768K
//    class space    used 281K, capacity 386K, committed 512K, reserved 1048576K

//    总结：S0 的空间如果不足以装下所有 Eden + S1 中存活下来的对象，但可以装下部分对象，则此部分对象转移到 S0 区，其余直接转移到老年代，
//    保证一方（Eden + S1）中没有任何剩余对象。然后新分配的内存装在 Eden 区（前提是 Eden 足够空间，否者新分配的内存一起转移到老年代）。

}








