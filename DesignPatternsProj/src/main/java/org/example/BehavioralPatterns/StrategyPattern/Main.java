package org.example.BehavioralPatterns.StrategyPattern;

public class Main {

    public static void main(String[] args) {
        int a = 100;
        int b = 10000;
        if(a < b)
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
