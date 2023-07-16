package org.example.CompositePattern;

public class Developer implements Employee{

    @Override
    public void showDetails() {
        System.out.println("showing details of dev");
    }
    public void writeCode(){
        System.out.println("dev writing code");
    }

}
