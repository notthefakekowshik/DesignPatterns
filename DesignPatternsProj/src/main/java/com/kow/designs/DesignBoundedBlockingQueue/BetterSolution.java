package com.kow.designs.DesignBoundedBlockingQueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MyBetterBoundedBlockingQueue<T> {
    int capacity;
    Queue<T> queue;
    Lock lock = new ReentrantLock();

    Condition fullRoom = lock.newCondition();
    Condition emptyRoom = lock.newCondition();

    MyBetterBoundedBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
    }

    public void put(T val) {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                System.out.println("QUEUE IS FULL! PLEASE STOP PRODUCING!!!!!");
                fullRoom.await();
            }
            queue.add(val);
            emptyRoom.signal();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T get() {
        lock.lock();
        try {
            while (queue.size() == 0) {
                System.out.println("QUEUE IS EMPTY!!! There is nothing to consume!");
                emptyRoom.await();
            }
            T val = queue.poll();
            fullRoom.signal();
            return val;
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }

        return null;
    }

}

public class BetterSolution {
    public static void main(String[] args) throws InterruptedException {
        MyBetterBoundedBlockingQueue<Integer> myBetterBoundedBlockingQueue = new MyBetterBoundedBlockingQueue<Integer>(
                5);

        Thread producerThread = new Thread(() -> {
            for (int i = 1; i < 50; i++) {
                System.out.println("PRODUCING: " + i);
                myBetterBoundedBlockingQueue.put(i);
            }
        }, "PRODUCER");

        Thread.sleep(3000);
        Thread consumerThread = new Thread(() -> {
            for (int i = 1; i < 50; i++) {
                System.out.println("CONSUMING: " + myBetterBoundedBlockingQueue.get());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }, "CONSUMER");

        producerThread.start();
        consumerThread.start();

        // wraps around. Circular line thing.
        int val = Integer.MAX_VALUE + 2;
        System.out.println(val);
    }

}
