package org.example.BehavioralPatterns.StrategyBetter;

public class BubbleSort implements ISort{

    @Override
    public void sort(int[] array) {

        int n = array.length;
        int temp = 0;
        for(int i = 0; i < n; i++){
            for(int j = 1; j < (n - i); j++){
                if(array[j - 1] > array[j]){
                    temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

}
