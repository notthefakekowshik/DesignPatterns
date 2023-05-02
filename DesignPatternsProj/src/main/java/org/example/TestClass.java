package org.example;

public class TestClass implements TestInterface1,TestInterface2{

    @Override
    public void testInterface1Method() {
        System.out.println("having fun with interface1");
    }

    @Override
    public void testInterface2Method() {
        System.out.println("having fun with interface2");
    }

}
