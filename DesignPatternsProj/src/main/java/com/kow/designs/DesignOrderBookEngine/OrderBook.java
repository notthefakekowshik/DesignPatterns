package com.kow.designs.DesignOrderBookEngine;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class OrderBook {

    private final PriorityQueue<Order> buyOrders = new PriorityQueue<>(OrderComparators.BUY_COMPARATOR);

    private final PriorityQueue<Order> sellOrders = new PriorityQueue<>(OrderComparators.SELL_COMPARATOR);

    private final Map<Long, Order> orderIndex = new HashMap<>();

    public OrderResponse processOrder(Order order) {
        orderIndex.put(order.getOrderId(), order);
        if (OrderType.BUY.equals(order.getOrderType())) {
            return orderMatchMakingAndProcess(order, sellOrders, buyOrders);
        }
        return  orderMatchMakingAndProcess(order, buyOrders, sellOrders);
    }


    /**
     *
     * @param ordersBookToLookAgainst -> (If it's a buy order, we should look in sell order book) and vice versa
     * @param ordersToAdd
     * @return
     */
    // not thread safe now, will make it later
    private OrderResponse orderMatchMakingAndProcess(Order currentOrder, PriorityQueue<Order> ordersBookToLookAgainst, PriorityQueue<Order> ordersToAdd) {
        long currentOrderPrice = currentOrder.getPrice();
        long initialIncomingQuantity = currentOrder.getQuantity();

        long currentIncomingQuantity = currentOrder.getQuantity();
        while (currentIncomingQuantity > 0 && !ordersBookToLookAgainst.isEmpty()) {
            Order potentialMatch = ordersBookToLookAgainst.peek();

            if (!potentialMatch.isActive()) {
                ordersBookToLookAgainst.poll();
                continue;
            }

            long currentPeekPrice = potentialMatch.getPrice();

            // There's no potential candidate for match making right now, save it for future.
            if (!canMatch(currentOrder, currentOrderPrice, currentPeekPrice)) {
                ordersToAdd.add(currentOrder);
                return new OrderResponse(OrderStatus.IN_PROGRESS, Instant.now().toEpochMilli(), initialIncomingQuantity, initialIncomingQuantity - currentOrder.getQuantity());
            }

            // There's potential candidate here.
            long currentAvailableQuantity = potentialMatch.getQuantity();

            long tradeQuantity = Math.min(currentIncomingQuantity, currentAvailableQuantity);

            potentialMatch.reduceQuantity(tradeQuantity);

            if (potentialMatch.getQuantity() == 0) {
                ordersBookToLookAgainst.poll();
            }

            currentIncomingQuantity -= tradeQuantity;
            currentOrder.reduceQuantity(tradeQuantity);
        }
        if (currentIncomingQuantity > 0) {
            ordersToAdd.add(currentOrder);
            return new OrderResponse(OrderStatus.IN_PROGRESS, Instant.now().toEpochMilli(), initialIncomingQuantity, initialIncomingQuantity - currentIncomingQuantity);
        }
        return new OrderResponse(OrderStatus.CLOSED, Instant.now().toEpochMilli(), initialIncomingQuantity, currentIncomingQuantity);
    }

    public OrderResponse cancelOrder(long orderId) {
        Order order = orderIndex.get(orderId);
        if (order != null) {
            order.cancel();
            orderIndex.remove(orderId);
            return new OrderResponse(OrderStatus.CANCELED, Instant.now().toEpochMilli(), order.getQuantity(), order.getQuantity());
        }
        // may be we can return better response, but using this for temporary.
        return new OrderResponse(OrderStatus.CLOSED, Instant.now().toEpochMilli(), 0, 0);
    }

    private boolean canMatch(Order currentOrder, long currentOrderPrice, long currentPeekPrice) {
        if (OrderType.BUY.equals(currentOrder.getOrderType())) {
            return currentOrderPrice >= currentPeekPrice;
        }
        return currentOrderPrice <= currentPeekPrice;
    }


}
