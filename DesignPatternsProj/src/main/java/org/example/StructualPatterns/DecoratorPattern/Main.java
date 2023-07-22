package org.example.StructualPatterns.DecoratorPattern;

public class Main {

    public static void main(String[] args) {
        Dosa dosa1 = new PlainDosa();
        dosa1 = new OnionDosa(dosa1);
        System.out.println(dosa1.getDetails() + " and the price is " + dosa1.getPrice());

//        dosa1 = new MasalaDosa(dosa1);
//        System.out.println(dosa1.getDetails() + " and the price is " + dosa1.getPrice());
    }
}
/*
Decorator pattern :
modify the state of the object dynamically.
Example :
there is a dosa, dosas are of different types. we cant create every class for that.
how will dosa be prepared?
you will create a plain dosa first and later you will start decorating the same old plain dosa.
be like masala dosa, onion dosa, etc etc.

 */
