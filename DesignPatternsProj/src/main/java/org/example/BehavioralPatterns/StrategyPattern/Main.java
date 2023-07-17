package org.example.BehavioralPatterns.StrategyPattern;

public class Main {

    public static void main(String[] args) {
        if(1000 < 100000)
        {
            Strategy strategy = new CreditCardStrategy();
            PaymentProcessor paymentProcessor = new PaymentProcessor(strategy);
            paymentProcessor.makePayment();
        }
        else{
            Strategy strategy = new DebitCardStrategy();
            PaymentProcessor paymentProcessor = new PaymentProcessor(strategy);
            paymentProcessor.makePayment();
        }

    }
}
