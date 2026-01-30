package org.example;

class Vehicle {
    private String engineName;

    public void setEngineName(String engineName) {
        this.engineName = engineName;
    }

    public String getEngineName() {
        return engineName;
    }

    public void startEngine() {
        System.out.println("in vehicle starting engine");
    }

}
class Bike extends Vehicle {

    public void startEngine() {
        System.out.printf("Engine name is %s and the bike is started.", this.getEngineName());
        System.out.println();
    }
}

class Bicycle extends Vehicle {
    public void startEngine() {
        throw new IllegalArgumentException("cant start engine for bicycle");
    }
}

public class BrokeLiskovImplDemo {

    public static void main(String[] args) {
        Vehicle bike = new Bicycle();
        bike.setEngineName("Bike Engine");
        bike.startEngine();
    }
}
/*

Liskov :
Objects of a super class should be replaceable with objects of its subclasses without breaking the application. in this case, it breaks the LS rule. cant have bicycle has the object.
 */