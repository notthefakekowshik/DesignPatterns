package org.example.BehavioralPatterns.MediatorDesignPattern;

public interface Mediator {

    void sendMessageToOtherEntity(String message, Student toStudent, Student fromStudent);
}
