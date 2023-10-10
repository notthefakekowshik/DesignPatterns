package org.example.TicTacToe.Exceptions;

public class CantPlacePieceException extends Exception{
    public CantPlacePieceException(String msg) {
        super(msg);
    }
}
