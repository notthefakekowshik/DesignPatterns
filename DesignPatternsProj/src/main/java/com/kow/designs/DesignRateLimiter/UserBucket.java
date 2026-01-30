package com.kow.designs.DesignRateLimiter;

import java.time.Instant;

public class UserBucket {
    private String userId;
    private Instant lastAccessTime;

    public UserBucket(String userId, Instant lastAccessTime) {
        this.userId = userId;
        this.lastAccessTime = Instant.now();
    }

    public String getUserId() {
        return userId;
    }

    public Instant getLastAccessTime() {
        return lastAccessTime;
    }
}
