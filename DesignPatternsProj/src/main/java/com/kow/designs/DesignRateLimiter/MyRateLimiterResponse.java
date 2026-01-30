package com.kow.designs.DesignRateLimiter;

import java.time.Instant;

public class MyRateLimiterResponse {
    private boolean isAllowed;
    private long remainingCapacity;
    private Instant resetTime;

    public MyRateLimiterResponse(boolean isAllowed, long remainingCapacity, Instant resetTime) {
        this.isAllowed = isAllowed;
        this.remainingCapacity = remainingCapacity;
        this.resetTime = resetTime;
    }

    public boolean isAllowed() {
        return this.isAllowed;
    }

    public long getRemainingCapacity() {
        return this.remainingCapacity;
    }

    public Instant getResetTime() {
        return this.resetTime;
    }
}
