//package org.example.CreationalPatterns.BuilderPattern.HouseExample;
//
//import lombok.Data;
//
//@Data
//class House{
//    String pool;
//    int numberOfFloors;
//    boolean isLiftAvailable;
//
//    boolean isBarAvaiable;
//
//}
//class HouseBuilder{
//    House house;
//    HouseBuilder()
//    {
//        this.house = new House();
//    }
//
//    HouseBuilder setSwimmingPool(String pool){
//        this.house.setPool(pool);
//        return this;
//    }
//
//    HouseBuilder setNumberOfFloors(int floors){
//        this.house.setNumberOfFloors(floors);
//        return  this;
//    }
//
//    HouseBuilder setLiftAvailability(boolean isLiftAvailable){
//        this.house.setLiftAvailable(isLiftAvailable);
//        return this;
//    }
//
//    HouseBuilder setBarAvailability(boolean isBarAvailable){
//        this.house.setBarAvaiable(isBarAvailable);
//        return this;
//    }
//
//    House buildHouseBuilder(){
//        return house;
//    }
//
//}
//public class Main {
//
//    public static void main(String[] args) {
//        House kowshikHouse = new HouseBuilder().setLiftAvailability(false).setNumberOfFloors(5).setSwimmingPool("K-pool").setBarAvailability(false).buildHouseBuilder();
//        System.out.println(kowshikHouse.toString());
//
//        House elonHouse = new HouseBuilder().setLiftAvailability(true).setNumberOfFloors(69).setSwimmingPool("E-pool").setBarAvailability(true).buildHouseBuilder();
//        System.out.println(elonHouse.toString());
//
//
//    }
//}
