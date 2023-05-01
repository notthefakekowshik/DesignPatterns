package org.example;

import implClasses.Axis;
import implClasses.HDFC;
import implClasses.Icici;

public class FactoryPatternApi {
    String bankName;
    FactoryPatternApi(String bankName)
    {
        this.bankName = bankName;
    }
    public Bank getBankInstance()
    {
        if(this.bankName == "HDFC")
        {
            return new HDFC();
        }
        else if(this.bankName == "AXIS")
        {
            return new Axis();
        }
        else if(this.bankName == "ICICI")
        {
            return new Icici();
        }
        else {
            return null;
        }
    }
}

/*
    It defined an interface or an abstract class for creating an object but subclasses decide which class to instantiate.
    This provides loose coupling.
    When should I use Factory pattern?
    1. When a class doesn't know what sub classes will be required to create.
    2. When a class wants that its sub-classes specify the objects to be created.
    3. When the parent classes choose the creation of objects to its sub-classes.

    Suppose ipdu, bank ane interface undi.
    There are HDFC bank, axis, icici etc etc etc.
    Then if a particular bank implements the bank interface, then we can give an instance to the user of a particular bank when they use that.

    Think like this,
    There's a factory which will produce the instances for you if you mention the type you want but it will not provide the internal implementation.
    This pattern is same as this.

    public interface Animal {
        void makeSound();
    }

    public class Dog implements Animal {
        public void makeSound() {
            System.out.println("Woof!");
        }
    }

    public class Cat implements Animal {
        public void makeSound() {
            System.out.println("Meow!");
        }
    }

    public class AnimalFactory {
        public static Animal createAnimal(String type) {
            if (type.equals("dog")) {
                return new Dog();
            } else if (type.equals("cat")) {
                return new Cat();
            } else {
                throw new IllegalArgumentException("Invalid animal type: " + type);
            }
        }
    }

    public class Main {
        public static void main(String[] args) {
            Animal dog = AnimalFactory.createAnimal("dog");
            dog.makeSound(); // Output: Woof!

            Animal cat = AnimalFactory.createAnimal("cat");
            cat.makeSound(); // Output: Meow!
        }
    }

    You go the main factory and say that I want an object of dog, then it produces for you and with that you can make the dog walk,scream, what ever you want.
    but do you know how dog object is being created? No.

 */
