package com.kow.designs.DesignOrderBookEngine;

import java.time.Instant;

public class main {
    public static void main(String[] args) {
        // assume some auto generator is present instead of hardcoding here or in the order class itself.
        OrderBook orderBook = new OrderBook();
        orderBook.processOrder(new Order(
                1,
                OrderType.BUY,
                500.20,
                10,
                Instant.now().toEpochMilli()
        ));
    }
}
