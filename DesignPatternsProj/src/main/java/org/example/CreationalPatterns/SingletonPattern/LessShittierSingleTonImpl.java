package org.example.CreationalPatterns.SingletonPattern;

public class LessShittierSingleTonImpl {
    public LessShittierSingleTonImpl lessShittierSingleTon;
    private LessShittierSingleTonImpl()
    {

    }

    public synchronized LessShittierSingleTonImpl getLessShittierSingleTon(){
        if(lessShittierSingleTon == null){
            return new LessShittierSingleTonImpl();
        }
        return lessShittierSingleTon;
    }

}
