package com.kow.designs.DesignInMemoryCache;

import java.util.HashMap;
import java.util.Map;

public class NotThreadSafeMyLRUCache<K, V> implements ICache<K, V> {


    private final Map<K, Node<K, V>> map;
    private final int capacity;
    private final Node<K, V> head;
    private final Node<K, V> tail;

    public NotThreadSafeMyLRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();

        // Initialize dummy nodes to avoid null checks in add/remove logic
        this.head = new Node<>(null, null);
        this.tail = new Node<>(null, null);

        // Connect head and tail
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    @Override
    public V get(K key) {
        if (!map.containsKey(key)) {
            return null;
        }

        Node<K, V> node = map.get(key);

        // Logic: Accessing an item makes it the Most Recently Used.
        // 1. Remove from current position
        removeNode(node);
        // 2. Add to front (head)
        addNode(node);

        return node.value;
    }

    @Override
    public void put(K key, V value) {
        // Create node
        if (!map.containsKey(key)) {
            // cache is full, EVICT!!!
            if (map.size() >= capacity) {
                // remove the last node
                Node<K, V> tailNode = tail.prev;
                removeNode(tailNode);
            }
            Node<K, V> node = new Node<>(key, value);
            addNode(node);
        }
        // update node value
        else {
            Node<K, V> node = map.get(key);
            node.value = value;
            removeNode(node);
            addNode(node);
        }
    }

    @Override
    public boolean remove(K key) {
        if (!map.containsKey(key)) {
            return false;
        }
        Node<K, V> node = map.get(key);
        Node<K, V> prev = node.prev;
        Node<K, V> next = node.next;

        prev.next = next;
        next.prev = prev;
        return true;
    }


    private void removeNode(Node<K, V> node) {
        map.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addNode(Node<K, V> node) {
        map.put(node.key, node);
        node.next = head.next;
        node.next.prev = node.prev;
    }
}

/*

First approach that comes to my mind is
Hashmap + queue

Hashmap -> get operation in O(1)
queue -> can be used for either add() or poll() - > O(1)

remove -> this will be a bottleneck, removing from map is easy but removing from queue takes O(N) in the worst case

 */
