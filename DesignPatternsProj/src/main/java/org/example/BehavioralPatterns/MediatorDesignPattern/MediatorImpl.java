package org.example.BehavioralPatterns.MediatorDesignPattern;

public class MediatorImpl implements Mediator{
    @Override
    public void sendMessageToOtherEntity(String message, Student toStudent, Student fromStudent) {
        message += " PAYLOAD ADDED BY MEDIATOR!! ";
        toStudent.receiveMessage(message, fromStudent);
    }

}
