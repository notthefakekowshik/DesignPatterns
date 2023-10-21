package org.example.BehavioralPatterns.MediatorDesignPattern;

public class StudentEntity {
    private String name;
    private char section;

    protected StudentEntity(String name, char section) {
        this.name = name;
        this.section = section;
    }

    private StudentEntity() {

    }

}
