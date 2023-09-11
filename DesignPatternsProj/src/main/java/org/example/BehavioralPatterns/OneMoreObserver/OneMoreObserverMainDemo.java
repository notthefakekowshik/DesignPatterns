package org.example.BehavioralPatterns.OneMoreObserver;

public class OneMoreObserverMainDemo {

    public static void main(String[] args) {
        Subscriber kowshik = new Subscriber("kowshik",23,"Hyd",'M');
        Subscriber elon = new Subscriber("Elon" , 69,"US", 'M');
        Subscriber stokes = new Subscriber("ben stokes" , 40 , "UK" , 'M');
        Publisher pub = new Publisher();
        pub.addSubscriber(kowshik);
        pub.addSubscriber(elon);
        pub.addSubscriber(stokes);
    }
}
