package com.greatestsasha.training.leetcode;

import java.util.Optional;

public class ConditionExample {

    private static final int CAPACITY = 5;
    private final NaiveStack<Integer> stack = new NaiveStack<>();
    private final ResourceLock lock = new ResourceLock();
    private final ResourceLock.Condition notFull = lock.newCondition();
    private final ResourceLock.Condition notEmpty = lock.newCondition();

    public void push(Integer item) throws InterruptedException {
        System.out.println("pushing " + item);
        lock.lock();
        try {
            while (stack.getSize() == CAPACITY) {
                notFull.await();
            }
            stack.push(item);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Integer pop() throws InterruptedException {
        System.out.println("poping ...");
        lock.lock();
        try {
            while (stack.getSize() == 0) {
                notEmpty.await();
            }
            int item = stack.pop();
            notFull.signal();
            return item;
        } finally {
            lock.unlock();
        }
    }

    public Optional<Integer> peek() throws InterruptedException {
        lock.lock();
        try {
            return stack.peek();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionExample example = new ConditionExample();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    example.push(i);
                    System.out.println("Pushed: " + i);

                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    example.pop();
                    System.out.println("Poped: " + i);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

}