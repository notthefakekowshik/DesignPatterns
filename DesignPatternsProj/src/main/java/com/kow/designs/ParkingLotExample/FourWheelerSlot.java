package com.kow.designs.ParkingLotExample;

import java.util.Date;

public class FourWheelerSlot extends ParkingSlot {

    public FourWheelerSlot(int id, String name, String vehicleNumber, Date enteredTime, VehicleTypeEnum vehicleType,
            int slotsLeft) {
        super(id, name, vehicleNumber, enteredTime, vehicleType, slotsLeft);
    }

}
