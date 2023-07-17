package org.example.CreationalPatterns.Singleton;

public class SingleTonEager {

    private static SingleTonEager singleTonEager = new SingleTonEager();
    private SingleTonEager(){}

    public synchronized SingleTonEager getSingleTonEager(){
        return singleTonEager;
    }


}
