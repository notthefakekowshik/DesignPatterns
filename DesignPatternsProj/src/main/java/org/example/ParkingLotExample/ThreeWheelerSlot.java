package org.example.ParkingLotExample;

import java.util.Date;

public class ThreeWheelerSlot extends ParkingSlot{
    public ThreeWheelerSlot(int id, String name, String vehicleNumber, Date enteredTime, VehicleTypeEnum vehicleType, int slotsLeft) {
        super(id, name, vehicleNumber, enteredTime, vehicleType, slotsLeft);
    }
}
