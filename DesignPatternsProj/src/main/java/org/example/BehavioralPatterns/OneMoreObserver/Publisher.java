package org.example.BehavioralPatterns.OneMoreObserver;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    List<Subscriber> subscriberList = new ArrayList<>();

    public void addSubscriber(Subscriber subscriber) {
        notifyCurrentSubscibers(subscriber);
        subscriberList.add(subscriber);
    }

    private void notifyCurrentSubscibers(Subscriber subscriber) {
        for(Subscriber sub : subscriberList) {
            System.out.printf("hello %s, %s is added to the subscriber list" , sub.getName() , subscriber.getName());
            System.out.println();
        }
        if(subscriberList.size()!=0) {
            System.out.println("notifying completed");
        }
        System.out.println();

    }

}
