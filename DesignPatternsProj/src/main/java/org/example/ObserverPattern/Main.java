package org.example.ObserverPattern;

public class Main {

    public static void main(String[] args) {
        Observer kowshikConsumer = new Consumer("kowshik");
        Observer elonConsumer = new Consumer("Elon");

        Subject sub = new Subject();
        sub.setConsumer(kowshikConsumer);
        sub.setConsumer(elonConsumer);
        sub.incrementCount();
        sub.incrementCount();
        sub.incrementCount();
        sub.incrementCount();
        sub.incrementCount();
        sub.incrementCount();
    }
}
