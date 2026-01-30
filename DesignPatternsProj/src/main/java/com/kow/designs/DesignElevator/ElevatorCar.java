package com.kow.designs.DesignElevator;

public class ElevatorCar {
    private int id;
    private int currentFloor;
    private Direction direction;
    private Door door;
    private InternalButton internalButton;
    private InternalRequest internalRequest;

    ElevatorCar(int id) {
        this.id = id;
        this.currentFloor = 0;
    }

    public int getId() {
        return this.id;
    }

}
