package org.example.StructualPatterns.DecoratorPattern.CoffeeExample;

// Component interface
interface Coffee {
    double cost();
    String description();
}

// Concrete component
class SimpleCoffee implements Coffee {
    @Override
    public double cost() {
        return 2.0;
    }

    @Override
    public String description() {
        return "Simple Coffee";
    }
}

// Decorator abstract class
abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee decoratedCoffee) {
        this.decoratedCoffee = decoratedCoffee;
    }
}

// Concrete decorators
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee decoratedCoffee) {
        super(decoratedCoffee);
    }

    @Override
    public double cost() {
        return decoratedCoffee.cost() + 0.5;
    }

    @Override
    public String description() {
        return decoratedCoffee.description() + ", Milk";
    }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee decoratedCoffee) {
        super(decoratedCoffee);
    }

    @Override
    public double cost() {
        return decoratedCoffee.cost() + 0.2;
    }

    @Override
    public String description() {
        return decoratedCoffee.description() + ", Sugar";
    }
}

// Client code
public class CoffeeMain {
    public static void main(String[] args) {
        Coffee simpleCoffee = new SimpleCoffee();
        System.out.println("Simple Coffee: " + simpleCoffee.description() + " Cost: $" + simpleCoffee.cost());

        Coffee milkCoffee = new MilkDecorator(simpleCoffee);
        System.out.println("Milk Coffee: " + milkCoffee.description() + " Cost: $" + milkCoffee.cost());

        Coffee sugarMilkCoffee = new SugarDecorator(milkCoffee);
        System.out.println("Sugar Milk Coffee: " + sugarMilkCoffee.description() + " Cost: $" + sugarMilkCoffee.cost());
    }
}
