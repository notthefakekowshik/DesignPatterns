package org.example.BehavioralPatterns.StrategyPattern;

public class DebitCardStrategy implements Strategy{

    @Override
    public void makePayment() {
        System.out.println("made payment using debit card");
    }

}
