package org.example.BehavioralPatterns.ObserverPattern;

public class Consumer implements Observer{
    String name;
    Consumer(String name)
    {
        this.name = name;
    }
    public void update()
    {
        System.out.println(String.format("Notifying '%s' that stock is available", this.name));
    }
}
