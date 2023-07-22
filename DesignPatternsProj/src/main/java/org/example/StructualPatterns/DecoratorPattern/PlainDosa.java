package org.example.StructualPatterns.DecoratorPattern;

public class PlainDosa implements Dosa{

    @Override
    public String getDetails() {

        return "Plain dosa";
    }

    @Override
    public int getPrice() {
        return 40;
    }

}
