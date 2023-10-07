package org.example.CreationalPatterns.AbstractPattern;


// Abstract product A
interface ProductA {
    void doSomething();
}

// Concrete product A1
class ConcreteProductA1 implements ProductA {
    @Override
    public void doSomething() {
        System.out.println("ConcreteProductA1: Doing something");
    }
}

// Concrete product A2
class ConcreteProductA2 implements ProductA {
    @Override
    public void doSomething() {
        System.out.println("ConcreteProductA2: Doing something");
    }
}

// Abstract product B
interface ProductB {
    void doSomething();
}

// Concrete product B1
class ConcreteProductB1 implements ProductB {
    @Override
    public void doSomething() {
        System.out.println("ConcreteProductB1: Doing something");
    }
}

// Concrete product B2
class ConcreteProductB2 implements ProductB {
    @Override
    public void doSomething() {
        System.out.println("ConcreteProductB2: Doing something");
    }
}

// Abstract factory
interface   AbstractFactory {
    ProductA createProductA();

    ProductB createProductB();
}

// Concrete factory 1
class ConcreteFactory1 implements AbstractFactory {
    @Override
    public ProductA createProductA() {
        return new ConcreteProductA1();
    }

    @Override
    public ProductB createProductB() {
        return new ConcreteProductB1();
    }
}

// Concrete factory 2
class ConcreteFactory2 implements AbstractFactory {
    @Override
    public ProductA createProductA() {
        return new ConcreteProductA2();
    }

    @Override
    public ProductB createProductB() {
        return new ConcreteProductB2();
    }
}

// Client code
public class AbstractPattern {
    public static void main(String[] args) {
        AbstractFactory factory1 = new ConcreteFactory1();
        ProductA productA1 = factory1.createProductA();
        ProductB productB1 = factory1.createProductB();
        productA1.doSomething(); // Output: ConcreteProductA1: Doing something
        productB1.doSomething(); // Output: ConcreteProductB1: Doing something

        AbstractFactory factory2 = new ConcreteFactory2();
        ProductA productA2 = factory2.createProductA();
        ProductB productB2 = factory2.createProductB();
        productA2.doSomething(); // Output: ConcreteProductA2: Doing something
        productB2.doSomething(); // Output: ConcreteProductB2: Doing something
    }
}
