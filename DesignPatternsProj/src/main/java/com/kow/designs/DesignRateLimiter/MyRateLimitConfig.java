package com.kow.designs.DesignRateLimiter;

import java.util.HashMap;
import java.util.Map;

/*
{
  "endpoint": "/search",
  "algorithm": "TokenBucket",
  "algoConfig": {
    "capacity": 1000,
    "refillRatePerSecond": 10
  }
}
 */
public class MyRateLimitConfig {
    private String endPoint;
    private MyRateLimiterAlgorithm myRateLimiterAlgorithm;
    private Map<String, Map<String, Integer>> algorithmConfig = new HashMap<>();


    public MyRateLimitConfig(String endPoint,
                             MyRateLimiterAlgorithm myRateLimiterAlgorithm,
                             Map<String, Map<String, Integer>> algorithmConfig) {
        this.endPoint = endPoint;
        this.myRateLimiterAlgorithm = myRateLimiterAlgorithm;
        this.algorithmConfig = algorithmConfig;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public MyRateLimiterAlgorithm getMyRateLimiterAlgorithm() {
        return myRateLimiterAlgorithm;
    }

    public Map<String, Map<String, Integer>> getAlgorithmConfig() {
        return algorithmConfig;
    }
}
