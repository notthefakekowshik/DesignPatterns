package org.example.CreationalPatterns.AbstractPattern.AirCraftDesign;

/*
https://www.educative.io/courses/software-design-patterns-best-practices/factory-method-pattern
 */

interface IEngine {
    void start();
    String getName();
}

interface IWings {
    void prepareWings();
    String getType();
}

interface AirCraftFactory {
    IEngine createEngine();
    IWings createWings();
}


class F16Engine implements IEngine {
    private final String name;

    public F16Engine() {
        this.name = "F16 Engine";
    }

    @Override
    public void start() {
        System.out.println(name + " started.");
    }

    @Override
    public String getName() {
        return name;
    }
}

class F16Wings implements IWings {
    private final String type;

    public F16Wings() {
        this.type = "F16 Wings";
    }

    @Override
    public void prepareWings() {
        System.out.println(type + " prepared.");
    }

    @Override
    public String getType() {
        return type;
    }
}

class BoeingEngine implements IEngine {
    private final String name;

    public BoeingEngine() {
        this.name = "Boeing Engine";
    }

    @Override
    public void start() {
        System.out.println(name + " started.");
    }

    @Override
    public String getName() {
        return name;
    }
}

class BoeingWings implements IWings {
    private final String type;

    public BoeingWings() {
        this.type = "Boeing Wings";
    }

    @Override
    public void prepareWings() {
        System.out.println(type + " prepared.");
    }

    @Override
    public String getType() {
        return type;
    }
}

class F16Factory implements AirCraftFactory {

    @Override
    public IEngine createEngine() {
        return new F16Engine();
    }

    @Override
    public IWings createWings() {
        return new F16Wings();
    }
}

class BoeingFactory implements AirCraftFactory {

    @Override
    public IEngine createEngine() {
        return new BoeingEngine();
    }

    @Override
    public IWings createWings() {
        return new BoeingWings();
    }
}

class AirCraft {
    private IEngine engine;
    private IWings wings;

    public AirCraft(AirCraftFactory factory) {
        this.engine = factory.createEngine();
        this.wings = factory.createWings();
    }

    public void fly() {
        engine.start();
        wings.prepareWings();
        System.out.println("AirCraft with " + engine.getName() + " and " + wings.getType() + " is flying.");
    }
}

public class AirCraftImplClient {

    public static void main(String[] args) {
        // Create an F16 AirCraft
        AirCraftFactory f16Factory = new F16Factory();
        AirCraft f16AirCraft = new AirCraft(f16Factory);
        f16AirCraft.fly();

        // Create a Boeing AirCraft
        AirCraftFactory boeingFactory = new BoeingFactory();
        AirCraft boeingAirCraft = new AirCraft(boeingFactory);
        boeingAirCraft.fly();
    }
}
