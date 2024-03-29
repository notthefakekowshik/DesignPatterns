package org.example.StructualPatterns.DecoratorPattern;

public class MasalaDosa extends DosaDecorator{

    MasalaDosa(Dosa dosa) {
        super(dosa);
    }

    @Override
    public String getDetails() {
        return "Plain Dosa is decorated with Masala Dosa";
    }

    @Override
    public int getPrice() {
        return 60;
    }

}
