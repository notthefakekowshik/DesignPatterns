package org.example;

public class CatFactory implements AnimalFactory{

    @Override
    public Animal createAnimal() {
        return new Cat();
    }

    @Override
    public AnimalFood createAnimalFood() {
        return new CatFood();
    }

}
