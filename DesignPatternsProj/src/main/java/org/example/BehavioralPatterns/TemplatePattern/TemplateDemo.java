package org.example.BehavioralPatterns.TemplatePattern;

import java.io.PrintWriter;

public class TemplateDemo {

    public static void main(String[] args) {
        Game cricketGame = new Cricket();
        cricketGame.play();

        System.out.println("\nlets play 1.e4");
        cricketGame = new Chess();
        cricketGame.play();

        PrintWriter
    }
}
