package org.example.BehavioralPatterns.TemplatePattern;

public abstract class Game {

    abstract String startGame();

    abstract String endGame();

    abstract String startPlayingTheGame();

    public void play() {
        startGame();
        startPlayingTheGame();
        endGame();
    }
}
