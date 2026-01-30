package com.kow.designs.DesignOrderBookEngine;

import java.util.Comparator;

public class OrderComparators {

    // buy orders should always be sorted by price desc.
    // highest bidder must get highest priority,
    public static final Comparator<Order> BUY_COMPARATOR = (Order thisOrder, Order thatOrder) -> {
        if (thisOrder.getPrice() == thatOrder.getPrice()) {
            return Long.compare(thisOrder.getTimestamp(), thatOrder.getTimestamp());
        }
        return Long.compare(thatOrder.getPrice(), thisOrder.getPrice());
    };


    public static final Comparator<Order> SELL_COMPARATOR = (Order thisOrder, Order thatOrder) -> {
        if (thisOrder.getPrice() == thatOrder.getPrice()) {
            return Long.compare(thisOrder.getTimestamp(), thatOrder.getTimestamp());
        }
        return Long.compare(thisOrder.getPrice(), thatOrder.getPrice());
    };
}
