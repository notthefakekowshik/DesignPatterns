package com.kow.designs.DesignElevator;

import java.util.ArrayList;
import java.util.List;

public class Buliding {
    private List<Floor> listOfFloors = new ArrayList<>();
    private List<ElevatorCar> elevatorCarList = new ArrayList<>();

    public void setListOfFloors(List<Floor> listOfFloors) {
        this.listOfFloors = listOfFloors;
    }

    public void setElevatorCarList(List<ElevatorCar> elevatorCarList) {
        this.elevatorCarList = elevatorCarList;
    }

    public boolean removeElevatorCar(int id) {
        for(ElevatorCar it : elevatorCarList) {
            if(it.getId() == id) {
                elevatorCarList.remove(it);
                return true;
            }
        }
        return false;
    }

}
