package org.example.CreationalPatterns.Singleton;

public class BestSingleTon {

    public static BestSingleTon bestSingleTon;
    private BestSingleTon(){}

    public static BestSingleTon getBestSingleTon(){
        if(bestSingleTon == null)
        {
            synchronized (BestSingleTon.class){
                if(bestSingleTon == null)
                {
                    return new BestSingleTon();
                }
            }
        }
        return bestSingleTon;
    }
}
