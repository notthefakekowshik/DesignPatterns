package org.example;

public class DogFood implements AnimalFood {

    @Override
    public void produceAnimalFood() {
        System.out.println("dog food is on the way");
    }

}
