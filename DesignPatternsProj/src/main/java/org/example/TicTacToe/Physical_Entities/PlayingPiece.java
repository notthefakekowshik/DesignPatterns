package org.example.TicTacToe.Physical_Entities;

public class PlayingPiece {
    PieceType type;
    PlayingPiece(PieceType type) {
        this.type = type;
    }

    public PieceType getPieceType() {
        return this.type;
    }
}
