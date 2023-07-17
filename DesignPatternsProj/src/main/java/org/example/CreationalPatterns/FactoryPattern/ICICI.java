package org.example.CreationalPatterns.FactoryPattern;

public class ICICI implements BankFactory{

    @Override
    public void showDetails() {
        System.out.println("in the icici bank");
    }

}
