package org.example.BehavioralPatterns.TemplatePattern;

public class Cricket extends Game{

    @Override
    String startGame() {
        System.out.println("starting cricket game");
        return null;
    }

    @Override
    String endGame() {
        System.out.println("ending cricket game");
        return null;
    }

    @Override
    String startPlayingTheGame() {
        System.out.println("start playing the cricket game");
        return null;
    }

}
