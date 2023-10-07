package org.example.ParkingLotExample;

import java.util.Date;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ParkingSlot {
    private int id;
    private String name;
    private String vehicleNumber;
    private Date enteredTime;
    private VehicleTypeEnum vehicleType;
    private int slotsLeft;


}
