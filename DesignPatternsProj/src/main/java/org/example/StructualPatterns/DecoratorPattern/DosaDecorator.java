package org.example.StructualPatterns.DecoratorPattern;

public abstract class DosaDecorator implements Dosa {

    Dosa dosa;
    DosaDecorator(Dosa dosa){
        this.dosa = dosa;
    }
}
