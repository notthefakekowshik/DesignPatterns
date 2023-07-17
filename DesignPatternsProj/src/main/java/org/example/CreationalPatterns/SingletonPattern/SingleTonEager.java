package org.example.CreationalPatterns.SingletonPattern;

public class SingleTonEager {

    private static SingleTonEager singleTonEager = new SingleTonEager();
    private SingleTonEager(){}

    public synchronized SingleTonEager getSingleTonEager(){
        return singleTonEager;
    }


}
