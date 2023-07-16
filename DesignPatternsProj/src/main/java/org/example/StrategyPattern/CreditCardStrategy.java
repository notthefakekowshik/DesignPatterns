package org.example.StrategyPattern;

public class CreditCardStrategy implements Strategy{

    @Override
    public void makePayment() {
        System.out.println("made payment using CC");
    }




}
