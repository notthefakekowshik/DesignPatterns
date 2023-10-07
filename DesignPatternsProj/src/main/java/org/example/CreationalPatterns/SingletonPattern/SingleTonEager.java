package org.example.CreationalPatterns.SingletonPattern;

class SingleTonEagerr {

    private static SingleTonEagerr singleTonEager = new SingleTonEagerr();
    private SingleTonEagerr(){}

    public static synchronized SingleTonEagerr getSingleTonEager(){
        return singleTonEager;
    }

    public void display() {
        System.out.println("in the eager class");
    }

}
public class SingleTonEager {

    public static void main(String[] args) {
        SingleTonEagerr singleTonEagerr = SingleTonEagerr.getSingleTonEager();
        singleTonEagerr.display();
    }
}

/*
    Eager will solve MT issue upto some extent, when JVM loads the class, the object will be created. right?
    but what if we dont use the object? it is shit, then this solution fails.
 */
