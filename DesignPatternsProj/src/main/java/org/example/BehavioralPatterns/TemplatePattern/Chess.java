package org.example.BehavioralPatterns.TemplatePattern;

public class Chess extends Game{

    @Override
    String startGame() {
        System.out.println("starting chess game");
        return null;
    }

    @Override
    String endGame() {
        System.out.println("ending chess game");
        return null;
    }

    @Override
    String startPlayingTheGame() {
        System.out.println("start playing chess game");
        return null;
    }

}
