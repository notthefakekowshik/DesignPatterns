package com.kow.designs.TicTacToe.Physical_Entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Player {
    private String name;
    private PlayingPiece playingPiece;

}
