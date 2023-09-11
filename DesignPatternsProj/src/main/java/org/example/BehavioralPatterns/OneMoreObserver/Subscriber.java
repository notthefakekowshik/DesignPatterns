package org.example.BehavioralPatterns.OneMoreObserver;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Subscriber {
    private String name;
    private int age;
    private String city;
    private char gender;
}
