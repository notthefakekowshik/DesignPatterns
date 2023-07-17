package org.example.CreationalPatterns.FactoryPattern;

public class FactoryImpl {
    public static BankFactory createBank(String name) {
        if (name.equals("HDFC")) {
            return new HDFC();
        }
        if (name.equals("ICICI")) {
            return new ICICI();
        }
        throw new IllegalArgumentException("Invalid bank name: " + name);
    }
}
