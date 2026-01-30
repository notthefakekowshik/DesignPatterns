package org.example.BehavioralPatterns.StrategyBetter;

public class SortingContext {
    private ISort sortingTechnique;

    public SortingContext(ISort sortingTechnique) {
        this.sortingTechnique = sortingTechnique;
    }

    void sort(int[] arr) {
        sortingTechnique.sort(arr);
    }

}
