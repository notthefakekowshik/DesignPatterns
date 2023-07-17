package org.example.CreationalPatterns.SingletonPattern;

public class ShittestSingleTonImpl {

    public ShittestSingleTonImpl shittestSingleTon = null;
    private ShittestSingleTonImpl(){
    }

    public ShittestSingleTonImpl getShittestSingleTon()
    {
        if(shittestSingleTon == null)
        {
            shittestSingleTon = new ShittestSingleTonImpl();
        }
        return shittestSingleTon;
    }

    // this is the problem
}
