package com.kow.designs.DesignInMemoryCache;

/*

Complexity Analysis:
Time: Still $O(1)$.
Space: $O(N)$ (Nodes + HashMap entries).
Concurrency: High. If concurrencyLevel is 16, typically 16 threads can operate without blocking each other.



CONS: ( Drawbacks)
Imagine totalCapacity = 100 and segments = 10. Each segment holds 10 items.

What if our hash function is slightly uneven, or user keys follow a pattern where all keys land in Segment 0?

Segment 0 fills up (10 items) and starts evicting, while Segment 1 is empty. We are evicting items even though the global cache has space!

 */
public class ThreadSafeSegmentedMyLRUCache<K, V> implements ICache<K, V> {

    private final int segmentsCount;
    private final Segment<K, V>[] segments;

    @SuppressWarnings("unchecked")
    public ThreadSafeSegmentedMyLRUCache(int totalCapacity, int concurrencyLevel) {
        this.segmentsCount = concurrencyLevel;
        this.segments = new Segment[segmentsCount];

        // Distribute capacity evenly across segments
        int capPerSegment = (int) Math.ceil((double) totalCapacity / segmentsCount);

        for (int i = 0; i < segmentsCount; i++) {
            segments[i] = new Segment<>(capPerSegment);
        }
    }

    // commented here, come back later
    // Helper to find the right segment
    private int getSegmentIndex(K key) {
        // Use hashCode and ensure it's positive
        return Math.abs(key.hashCode() % segmentsCount);
    }

    @Override
    public V get(K key) {
        return segments[getSegmentIndex(key)].get(key);
    }

    @Override
    public void put(K key, V value) {
        segments[getSegmentIndex(key)].put(key, value);
    }

    @Override
    public boolean remove(K key) {
        return segments[getSegmentIndex(key)].remove(key);
    }
}
