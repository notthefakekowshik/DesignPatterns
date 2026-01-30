package com.kow.designs.DesignRateLimiter;

import java.time.Instant;

public class TokenBucketRateLimiterStrategy implements MyRateLimiterStrategy {

    @Override
    public MyRateLimiterResponse canAllowRequest(String userId) {
        return new MyRateLimiterResponse(
                true,
                someComplexCalculationToGetRemainingTokens(userId),
                Instant.now()
        );
    }

    private int someComplexCalculationToGetRemainingTokens(String userId) {
        return 9;
    }
}
