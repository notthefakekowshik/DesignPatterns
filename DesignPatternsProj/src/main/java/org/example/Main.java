package org.example;

public class Main {
    public void welcomeA()
    {
         System.out.println("welcome A");
    }
    public void welcomeB()
    {
        System.out.println("welcome B");
        new Main().welcomeA();
    }
    public static void main(String[] args) {
        int a = 1 ;
        int b = 2;
        System.out.println(a+b);
        new Main().welcomeB(); /* created these for learning debug points */
        SingleTonPattern obj1 = SingleTonPattern.SingleTonPatternObjFetcher();
        SingleTonPattern obj2 = SingleTonPattern.SingleTonPatternObjFetcher();
        System.out.println("object count is");
        System.out.println(SingleTonPattern.objectCount);


    }

}