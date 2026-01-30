package com.kow.designs.DesignRateLimiter;

public class MyRateLimiterFactory {

    public static MyRateLimiter getMyRateLimiter(MyRateLimiterAlgorithm myRateLimiterAlgorithm,
                                                 MyRateLimitConfig myRateLimitConfig) {
        MyRateLimiter myRateLimiter = new MyRateLimiter();
        myRateLimiter.setMyRateLimitConfig(myRateLimitConfig);

        // if else hell, can you use registry pattern. Fix it later.
        if (MyRateLimiterAlgorithm.TOKEN_BUCKET.equals(myRateLimiterAlgorithm)) {
            myRateLimiter.setMyRateLimiterStrategy(new TokenBucketRateLimiterStrategy());
        } else if (MyRateLimiterAlgorithm.LEAKY_BUCKET.equals(myRateLimiterAlgorithm)) {
            myRateLimiter.setMyRateLimiterStrategy(new TokenBucketRateLimiterStrategy());
        } else {

        }

        return myRateLimiter;
    }
}
