package org.example.CreationalPatterns.FactoryPattern;

public class main {

    public static void main(String[] args) {
        String name = "HDFC";
        FactoryImpl.createBank(name).showDetails();

    }
}
