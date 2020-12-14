package com.seniorlibs.thread.collection;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Author: chen
 * Version: 1.28
 * Date: 2020/3/8.
 * Mender:
 * Modify:
 * Description: 测试HashMap是否线程安全
 * <p>
 * java.util包下的HashSet和HashMap类不是线程安全的，java.util.concurrent包下的ConcurrentHashMap类是线程安全的。
 */
public class HashMapTest {

    private static final String TAG = "HashMapTest";

    /**
     * 多线程下，扩容期间取出的值不准确
     * HashMap 本身默认的容量不是很大，如果不停地往 map 中添加新的数据，它便会在合适的时机进行扩容。
     * 而在扩容期间，它会新建一个新的空数组，并且用旧的项填充到这个新的数组中去。那么，在这个填充的过程中，
     * 如果有线程获取值，很可能会取到 null 值，而不是我们所希望的、原来添加的值。
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void resizeBug() {
        final Map<Integer, String> map = new HashMap<>();

        final Integer targetKey = 0b1111_1111_1111_1111; // 65 535
        final String targetValue = "v";
        map.put(targetKey, targetValue);

        new Thread(() -> {
            IntStream.range(0, targetKey).forEach(key -> map.put(key, "someValue"));
        }).start();

        while (true) {
            if (null == map.get(targetKey)) {
                throw new RuntimeException("HashMap is not thread safe.");
            }
        }

        // Caused by: java.lang.RuntimeException: HashMap is not thread safe.
    }

    public static class HashMapKey {
        private Integer id;
        public HashMapKey(Integer id) {
            this.id = id;
        }
        public Integer getId() {
            return id;
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || !(o instanceof HashMapKey)) {
                return false;
            } else {
                return this.getId().equals(((HashMapKey) o).getId());
            }
        }
    }

    /**
     * 为什么HashMap的“key”部分存放自定义的对象时，需要重写equals()和hashcode()方法？
     */
    public static void testHashConflict() {
        HashMapKey k1 = new HashMapKey(1);
        HashMapKey k2 = new HashMapKey(1);
        HashMap<HashMapKey, String> map = new HashMap<>();
        map.put(k1, "急急急急急");
        System.out.println(TAG + " map.get(k2) : " + map.get(k2)); // map.get(k2) : null
    }

//    表初始化和大小调整控制。如果为负，则表将被初始化或调整大小：-1用于初始化，否则-（1 +活动的调整大小线程数）。
//    否则，当table为null时，保留创建时要使用的初始表大小，或者默认为0。初始化之后，保留下一个要调整表大小的元素计数值。
//    Table initialization and resizing control.  When negative, the table is being initialized or resized: -1 for initialization,
//            else -(1 + the number of active resizing threads).  Otherwise,
//            when table is null, holds the initial table size to use upon creation, or 0 for default.
//    After initialization, holds the next element count value upon which to resize the table.
}
