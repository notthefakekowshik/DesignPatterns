package com.kow.designs.DesignElevator;

import java.util.Arrays;

public class ElevatorDesignImpl {

    public static void main(String[] args) {
        Buliding buliding = new Buliding();

        Floor floor1 = new Floor(1);
        Floor floor2 = new Floor(2);
        Floor floor3 = new Floor(3);
        Floor floor4 = new Floor(4);
        Floor floor5 = new Floor(5);
        buliding.setListOfFloors(Arrays.asList(floor1, floor2, floor3, floor4, floor5));

        ElevatorCar elevatorCar1 = new ElevatorCar(1);
        ElevatorCar elevatorCar2 = new ElevatorCar(2);
        ElevatorCar elevatorCar3 = new ElevatorCar(3);

        buliding.setElevatorCarList(Arrays.asList(elevatorCar1, elevatorCar2, elevatorCar3));

        while (true) {
            System.out.println("1-5 floors?");

        }

    }
}
