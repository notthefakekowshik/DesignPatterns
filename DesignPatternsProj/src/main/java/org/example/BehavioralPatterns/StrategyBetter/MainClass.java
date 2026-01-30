package org.example.BehavioralPatterns.StrategyBetter;

import java.util.Arrays;

public class MainClass {

    public static void main(String[] args) {

        SortingContext context = new SortingContext(new BubbleSort());
        int a[] = new int[] {5,3,1,4,2};
        printMyArray(a);
        context.sort(a);
        printMyArray(a);
    }

    private static void printMyArray(int[] a) {
        System.out.println(Arrays.toString(a));
    }
}
