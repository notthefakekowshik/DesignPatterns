package com.kow.designs.DesignRateLimiter;

public interface MyRateLimiterStrategy {

    MyRateLimiterResponse canAllowRequest(String userId);

}
