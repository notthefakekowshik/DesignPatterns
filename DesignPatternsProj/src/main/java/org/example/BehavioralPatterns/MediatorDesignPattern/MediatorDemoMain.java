package org.example.BehavioralPatterns.MediatorDesignPattern;

public class MediatorDemoMain {

    public static void main(String[] args) {
        StudentFromClassA kowshik = new StudentFromClassA("kowshik", 'A');
        StudentFromClassB elon = new StudentFromClassB("elon" , 'B');
        kowshik.sendMessage("Hello elon, How are you doing?" , elon);
        elon.sendMessage("hello kowshik, I am good. what about you? ", kowshik);
    }
}
