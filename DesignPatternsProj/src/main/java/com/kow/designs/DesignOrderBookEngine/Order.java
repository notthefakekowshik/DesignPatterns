package com.kow.designs.DesignOrderBookEngine;

import lombok.Getter;

import java.time.Instant;

@Getter
public class Order {

    private final long orderId;
    private final OrderType orderType;
    private long quantity;
    private long price; // why long price? why not double? Ans : Read below docs
    private final long timestamp;
    private final double rawPrice;
    private boolean active = true;

    public Order(long orderId, OrderType orderType, double rawPrice, long quantity, long timestamp) {
        this.orderId = orderId;
        this.orderType = orderType;
        this.price = Math.round(rawPrice * 100);
        this.rawPrice = rawPrice;
        this.quantity = quantity;
        this.timestamp = timestamp;
    }

    public void reduceQuantity(long amount) {
        this.quantity -= amount;
    }

    // updating the order price, this violated YAGNI (FYI), I am aware of it.
    public void setPrice(long price) {
        this.price = price;
    }

    // while displaying, we must not display our long price, it's more of a hack to have better performance and reliable data.
    public double displayPrice() {
        return getRawPrice();
    }

    public void cancel() {
        this.active = false;
    }
}



/*

How should we store Price?

Option A: double / float

    Junior approach.

    Problem: Floating point arithmetic is imprecise. In Java, 0.1 + 0.2 often results in 0.30000000000000004. In a financial system handling crores, these tiny errors compound and money disappears. Never use double for money.

Option B: BigDecimal

    Mid-level approach.

    Pros: Perfect precision. new BigDecimal("10.55").

    Cons: It creates objects on the heap (Garbage Collection pressure) and math operations are slow compared to primitives. For a high-frequency trading engine (like Angel One), this might be too slow.

Option C: long (Scaled Integers)

    Senior/HFT approach.

    Strategy: We store prices in "paisa" or "cents".

    ₹100.50 becomes 10050.

    Pros: It's a primitive. Math is lightning fast (CPU native). Zero garbage collection.

    Cons: You have to remember to scale/unscale when displaying to the user.

Decision for this Interview: Since we are building a Low Latency engine, let's go with Option C (long) for Price. It shows you understand performance trade-offs.

 */
