package com.kow.designs.DesignOrderBookEngine;

import lombok.Getter;

@Getter
public class OrderResponse {
    private OrderStatus orderStatus;
    private final long timeStamp;
    private final long initialQuantity;
    private final long remainingQuantity;

    public OrderResponse(OrderStatus orderStatus, long timeStamp,
                         long initialQuantity, long remainingQuantity) {
        this.orderStatus = orderStatus;
        this.timeStamp = timeStamp;
        this.initialQuantity = initialQuantity;
        this.remainingQuantity = remainingQuantity;
    }
}
