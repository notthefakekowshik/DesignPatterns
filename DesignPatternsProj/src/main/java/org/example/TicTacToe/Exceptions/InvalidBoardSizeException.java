package org.example.TicTacToe.Exceptions;

public class InvalidBoardSizeException extends Exception{
    public InvalidBoardSizeException(String msg) {
        super(msg);
    }
}
