package org.example.CreationalPatterns.PrototypePattern;

public class Main {

    public static void main(String[] args) {
        Student ElonMusk = new Student("Elon" , 69, "Mars");
        Student elonMuskClone = ElonMusk.getClone();

        System.out.println(ElonMusk.getPlanet());
        System.out.println(elonMuskClone.getPlanet());

        elonMuskClone.setPlanet("Earth");

        System.out.println(ElonMusk.getPlanet());
        System.out.println(elonMuskClone.getPlanet());

        /*
            OUTPUT :
            Mars
            null
            Mars
            Earth

         */
    }
}
