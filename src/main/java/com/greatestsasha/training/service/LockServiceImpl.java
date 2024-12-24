//package com.greatestsasha.training.service;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.integration.support.locks.ExpirableLockRegistry;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.locks.Lock;
//
//@Slf4j
//@Component
//public class LockServiceImpl implements LockService {
//
//    @Qualifier("LOCK_REGISTRY_BEAN")
//    @Autowired
//    private ExpirableLockRegistry lockRegistry;
//
//    @Lazy
//    @Autowired
//    private LockServiceImpl self;
//
//    @Override
//    public void runSyncTransactional(Identifiable request, Runnable runnable) {//todo real request!
//        Lock lock = lockRegistry.obtain(request.getId());
//        lock.lock();
//        try {
//            self.runTransactional(runnable);
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    @Override
//    public void runTransactional(Runnable runnable) {
//        runnable.run();
//    }
//}