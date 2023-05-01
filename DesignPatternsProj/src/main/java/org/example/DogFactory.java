package org.example;

public class DogFactory implements AnimalFactory{

    @Override
    public Animal createAnimal() {
        return new Dog();
    }

    @Override
    public AnimalFood createAnimalFood() {
        return new DogFood();
    }

}
