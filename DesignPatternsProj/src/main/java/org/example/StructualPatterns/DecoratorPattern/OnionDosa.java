package org.example.StructualPatterns.DecoratorPattern;

public class OnionDosa extends DosaDecorator{

    OnionDosa(Dosa dosa) {
        super(dosa);
    }

    @Override
    public String getDetails() {
        return dosa.getDetails() + " is decorated with Onion dosa";
    }

    @Override
    public int getPrice() {
        return dosa.getPrice() + 10;
    }

}
