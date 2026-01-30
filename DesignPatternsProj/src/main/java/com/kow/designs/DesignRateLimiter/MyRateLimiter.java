package com.kow.designs.DesignRateLimiter;

import java.util.HashMap;
import java.util.Map;

public class MyRateLimiter {
    private Map<String, MyRateLimitConfig> listOfEndPointsWithConfig = new HashMap<>();
    private MyRateLimiterStrategy myRateLimiterStrategy;

    public void setMyRateLimitConfig(MyRateLimitConfig config) {
        listOfEndPointsWithConfig.put(config.getEndPoint(), config);
    }

    public MyRateLimiterResponse canAllowRequest(String url, String userId) {
        return myRateLimiterStrategy.canAllowRequest(userId);
    }

    public void setMyRateLimiterStrategy(MyRateLimiterStrategy myRateLimiterStrategy) {
        this.myRateLimiterStrategy = myRateLimiterStrategy;
    }
}
