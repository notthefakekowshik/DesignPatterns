package com.kow.designs.DesignBoundedBlockingQueue;

import java.util.LinkedList;
import java.util.Queue;

class MyBruteForceBoundedBlockingQueue<T> {
    int capacity;
    Queue<T> queue;

    MyBruteForceBoundedBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
    }

    public synchronized void put(T val) {
        try {
            while (queue.size() == capacity) {
                System.out.println("QUEUE IS FULL! PLEASE STOP PRODUCING!!!!!");
                wait();
            }
            queue.add(val);
            notifyAll();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public synchronized T get() {
        try {
            while (queue.size() == 0) {
                System.out.println("QUEUE IS EMPTY!!!!!");
                wait();
            }
            T val = queue.poll();
            notifyAll();
            return val;
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

public class BruteForceSolution {
    public static void main(String[] args) throws InterruptedException {
        MyBruteForceBoundedBlockingQueue<Integer> myBetterBoundedBlockingQueue = new MyBruteForceBoundedBlockingQueue<Integer>(
                5);

        Thread producerThread = new Thread(() -> {
            for (int i = 1; i < 50; i++) {
                System.out.println("PRODUCING: " + i);
                myBetterBoundedBlockingQueue.put(i);
            }
        }, "PRODUCER");

        Thread.sleep(1000);
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
    }
}
