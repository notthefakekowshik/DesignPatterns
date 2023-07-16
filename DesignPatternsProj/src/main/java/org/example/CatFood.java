package org.example;

public class CatFood implements AnimalFood {

    @Override
    public void produceAnimalFood() {
        System.out.println("cat is eating cat food");
    }

}
