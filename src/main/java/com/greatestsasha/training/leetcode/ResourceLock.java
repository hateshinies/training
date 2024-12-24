package com.greatestsasha.training.leetcode;

public class ResourceLock {

    private int reads = 0;
    private int writes = 0;
    private int writeRequests = 0;

    public synchronized void lock() throws InterruptedException {
        writeRequests++;
        while (reads > 0 || writes > 0) {
            wait();
        }
        writeRequests--;
        writes++;
    }

    public synchronized void unlock() {
        writes--;
        notifyAll();
    }

    public Condition newCondition() {
        return new Condition();
    }

    public class Condition {

        public synchronized void await() throws InterruptedException {
            while (writes > 0 || writeRequests > 0) {
                System.out.println("waiting");
                wait();
            }
            reads++;
        }

        public synchronized void signal() {
            reads--;
            System.out.println("wake up!");
            notifyAll();
        }
    }
}