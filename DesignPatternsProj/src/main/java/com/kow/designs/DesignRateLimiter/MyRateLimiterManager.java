package com.kow.designs.DesignRateLimiter;

import java.util.HashMap;

public class MyRateLimiterManager {
    public static void main(String[] args) {
        MyRateLimitConfig myRateLimitConfig = new MyRateLimitConfig(
                "/getProducts",
                MyRateLimiterAlgorithm.TOKEN_BUCKET,
                new HashMap<>() {{
                    put("algoConfig", new HashMap<>() {{
                        put("maxRPM", 1000);
                        put("refillRatePerSecond", 10);
                    }});
                }}
        );

        MyRateLimiter myRateLimiterWithTokenBucketStrategy = MyRateLimiterFactory.getMyRateLimiter(MyRateLimiterAlgorithm.TOKEN_BUCKET, myRateLimitConfig);

        myRateLimiterWithTokenBucketStrategy.canAllowRequest("/getProcuts", "kowshik");


    }
}
