package org.example;

abstract class Vehiclee {
    private String engineName;

    public void setEngineName(String engineName) {
        this.engineName = engineName;
    }

    public String getName() {
        return engineName;
    }

    public abstract void start();
}

interface EnginePowered {
    void setEngineName(String engineName);
    String getEngineName();
    void startEngine();
}

class Bikee extends Vehiclee implements EnginePowered {
    private String engineName;

    @Override
    public void setEngineName(String engineName) {
        this.engineName = engineName;
    }

    @Override
    public String getEngineName() {
        return engineName;
    }

    @Override
    public void startEngine() {
        System.out.printf("Engine name is %s and it is started.%n", this.getEngineName());
    }

    @Override
    public void start() {
        startEngine();
    }
}

class Bicyclee extends Vehiclee {

    @Override
    public void start() {
        System.out.println("Bicycle started. It has no engine.");
    }
}

public class ProperLiskovImpl {

    public static void main(String[] args) {
        Vehiclee bike = new Bikee();
        bike.setEngineName("Bike Engine");
        bike.start();

        Vehiclee bicycle = new Bicyclee();
        bicycle.start();

    }
}
