package org.example.CreationalPatterns.FactoryPattern;

public class HDFC implements BankFactory{

    @Override
    public void showDetails() {
        System.out.println("in the HDFC bank");
    }

}
