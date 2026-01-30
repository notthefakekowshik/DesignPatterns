package com.kow.designs.DesignInMemoryCache;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

class Segment<K, V> {
    private final int capacity;
    private final HashMap<K, Node<K, V>> map;
    private final Node<K, V> head;
    private final Node<K, V> tail;
    private final ReentrantLock lock;

    public Segment(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.lock = new ReentrantLock();

        // Initialize dummy nodes
        this.head = new Node<>(null, null);
        this.tail = new Node<>(null, null);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    // Standard LRU helper methods (must be called inside lock)
    private void removeNode(Node<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addNodeToHead(Node<K, V> node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    public V get(K key) {
        try {
            if (!map.containsKey(key)) {
                return null;
            }
            Node<K, V> node = map.get(key);
            // Move to MRU
            removeNode(node);
            addNodeToHead(node);
            return node.value;
        } finally {
            lock.unlock();
        }
    }

    public void put(K key, V value) {
        lock.lock();
        try {
            if (map.containsKey(key)) {
                // Update
                Node<K, V> node = map.get(key);
                node.value = value;
                removeNode(node);
                addNodeToHead(node);
            } else {
                // Insert
                if (map.size() >= capacity) {
                    // Evict LRU (node before tail)
                    Node<K, V> lru = tail.prev;
                    removeNode(lru);
                    map.remove(lru.key);
                }
                Node<K, V> newNode = new Node<>(key, value);
                addNodeToHead(newNode);
                map.put(key, newNode);
            }
        } finally {
            lock.unlock();
        }
    }

    public boolean remove(K key) {
        lock.lock();
        try {
            if (map.containsKey(key)) {
                Node<K, V> node = map.get(key);
                removeNode(node);
                map.remove(key);
                return true;
            }
        } finally {
            lock.unlock();
        }
        return false;
    }
}
