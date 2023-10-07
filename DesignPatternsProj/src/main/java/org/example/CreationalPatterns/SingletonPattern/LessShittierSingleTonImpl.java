package org.example.CreationalPatterns.SingletonPattern;

public class LessShittierSingleTonImpl {
    public LessShittierSingleTonImpl lessShittierSingleTon;
    private LessShittierSingleTonImpl()
    {

    }

    public synchronized LessShittierSingleTonImpl getLessShittierSingleTon(){
        /*
            this will introduce unnecessary sync overload. incase there is some code which is not related to obj creation, then other threads will wait outside the method.
            but those threads need not wait because it is not related to object creation.
            so this is also not the best.

         */
        if(lessShittierSingleTon == null){
            return new LessShittierSingleTonImpl();
        }
        return lessShittierSingleTon;
    }

    public static void main(String[] args) {

    }

}
