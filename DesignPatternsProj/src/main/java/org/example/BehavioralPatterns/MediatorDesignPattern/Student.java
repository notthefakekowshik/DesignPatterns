package org.example.BehavioralPatterns.MediatorDesignPattern;

public interface Student {

    MediatorImpl mediator = new MediatorImpl();
    default void sendMessage(String msg, Student stu) {
        mediator.sendMessageToOtherEntity(msg, stu, this);
    }
    default void receiveMessage(String msg, Student stu) {
        System.out.println("Message is : " +msg + " from -> " + stu.getClass());
    }
}
