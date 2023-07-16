package org.example.StrategyPattern;

public class PaymentProcessor {

    private Strategy strategy;
    public PaymentProcessor(Strategy strategy) {
        this.strategy = strategy;
    }

    public void makePayment()
    {
        strategy.makePayment();
    }

}
