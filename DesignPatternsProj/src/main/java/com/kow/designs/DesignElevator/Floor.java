package com.kow.designs.DesignElevator;

public class Floor {
    private int floorId;


    Floor(int floorId) {
        this.floorId = floorId;
    }

    public void sendExternalRequest(int floorId) {
        new ElevatorController().sendExternalRequest(floorId);
    }
}
