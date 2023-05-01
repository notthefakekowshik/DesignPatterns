package org.example;

public class SingleTonPattern {
    /*
        1. Make the constructor private.
     */

    private static SingleTonPattern singleTonPatternObj;
    public static int objectCount;
    private SingleTonPattern()
    {

    }
    public static SingleTonPattern SingleTonPatternObjFetcher()
    {
        if(singleTonPatternObj == null)
        {
            singleTonPatternObj = new SingleTonPattern();
            objectCount+=1;
        }
        return singleTonPatternObj;
    }

}
