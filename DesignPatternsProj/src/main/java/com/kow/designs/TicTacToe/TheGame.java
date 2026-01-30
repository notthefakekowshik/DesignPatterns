package com.kow.designs.TicTacToe;


import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

import com.kow.designs.TicTacToe.Exceptions.InvalidBoardSizeException;
import com.kow.designs.TicTacToe.Physical_Entities.Board;
import com.kow.designs.TicTacToe.Physical_Entities.PieceCircle;
import com.kow.designs.TicTacToe.Physical_Entities.PieceCross;
import com.kow.designs.TicTacToe.Physical_Entities.Player;

public class TheGame {

    Deque<Player> listOfPlayers;
    Board gameBoard;
    public void init(int boardSize) throws InvalidBoardSizeException {
        listOfPlayers = new LinkedList<>();
        Player kowshik = new Player("kowshik",new PieceCross());
        Player elon = new Player("elon", new PieceCircle());

        listOfPlayers.add(kowshik);
        listOfPlayers.add(elon);

        gameBoard = new Board(boardSize);
    }

    public void startGame() {
        boolean gameNotEnded = true;
        while(gameNotEnded) {
            gameBoard.printBoard();
            Player currPlayer = listOfPlayers.removeFirst();

            boolean isThePiecePlaced = false;
            boolean playerWon = false;

            while(!isThePiecePlaced) {
                System.out.println("Current player : " + currPlayer.getName() + " and the piece is : " +currPlayer.getPlayingPiece().getPieceType());
                System.out.println("Enter row");
                int row = new Scanner(System.in).nextInt();
                System.out.println("Enter column");
                int col = new Scanner(System.in).nextInt();
                isThePiecePlaced = gameBoard.placeThePiece(row, col, currPlayer.getPlayingPiece());
                if(!isThePiecePlaced) {
                    System.out.println("CANT PLACE THE PIECE!! Enter a proper cell value");
                }
                else {
                    playerWon = gameBoard.isThereAnyWinner(row, col, currPlayer.getPlayingPiece().getPieceType());
                }
            }
            if(playerWon) {
                System.out.println("CONGRATS!!!!");
                gameBoard.printBoard();
                System.out.println("Winner is : " + currPlayer.getName());
                break;
            }
            listOfPlayers.addLast(currPlayer);
        }
    }

}
