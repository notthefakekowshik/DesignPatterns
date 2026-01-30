package com.kow.designs.ParkingLotExample;

import java.util.Date;

public class TwoWheelerSlot extends ParkingSlot{

    private int price;

    public TwoWheelerSlot(int id, String name, String vehicleNumber, Date enteredTime, VehicleTypeEnum vehicleType, int slotsLeft) {
        super(id, name, vehicleNumber, enteredTime, vehicleType, slotsLeft);
    }

}
