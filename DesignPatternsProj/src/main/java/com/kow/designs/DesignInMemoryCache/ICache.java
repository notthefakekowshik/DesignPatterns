package com.kow.designs.DesignInMemoryCache;

public interface ICache<K, V> {
    V get(K key);
    void put(K key, V value);
    boolean remove(K key);
}
