package org.example.BehavioralPatterns.StrategyBetter;

import java.util.Arrays;

public class MergeSort implements ISort{

    @Override
    public void sort(int[] array) {
        Arrays.sort(array);
    }

}
