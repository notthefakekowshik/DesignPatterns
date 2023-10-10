package org.example.TicTacToe;

import org.example.TicTacToe.Exceptions.InvalidBoardSizeException;

public class MainClass {

    public static void main(String[] args) throws InvalidBoardSizeException {
        TheGame game = new TheGame();
        int boardSize = 3;
        game.init(boardSize);
        game.startGame();
    }
}
