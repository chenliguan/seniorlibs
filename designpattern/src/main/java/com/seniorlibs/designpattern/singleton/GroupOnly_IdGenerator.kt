package com.seniorlibs.designpattern.singleton

/**
 * 集群唯一递增 ID 号码生成器
 */
class GroupOnly_IdGenerator private constructor() {

//    public class IdGenerator {
//        private AtomicLong id = new AtomicLong(0);
//        private static IdGenerator instance;
//        private static SharedObjectStorage storage = FileSharedObjectStorage(/*入参省略，比如文件地址*/);
//        private static DistributedLock lock = new DistributedLock();
//
//        private IdGenerator() {}
//
//        public synchronized static IdGenerator getInstance()
//        if (instance == null) {
//            lock.lock();
//            instance = storage.load(IdGenerator.class);
//        }
//        return instance;
//    }
//
//    public synchroinzed void freeInstance() {
//        storage.save(this, IdGeneator.class);
//        instance = null; //释放对象
//        lock.unlock();
//    }
//
//    public long getId() {
//        return id.incrementAndGet();
//    }
//}
//
//// IdGenerator使用举例
//IdGenerator idGeneator = IdGenerator.getInstance();
//long id = idGenerator.getId();
//idGenerator.freeInstance();

}
