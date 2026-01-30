package com.kow.designs.TicTacToe.Physical_Entities;

import java.util.ArrayList;
import java.util.List;

import com.kow.designs.TicTacToe.Exceptions.InvalidBoardSizeException;
import com.kow.designs.TicTacToe.Utils.Pair;

public class Board {
    private int size;

    PlayingPiece[][] board;

    public Board(int size) throws InvalidBoardSizeException {
        if(size < 0) {
            throw new InvalidBoardSizeException("Board size is not valid");
        }
        this.size = size;
        board = new PlayingPiece[size][size];
    }

    public boolean placeThePiece(int row, int col, PlayingPiece playingPiece) {
        if (validateInputFields(row, col)) {
            board[row][col] = playingPiece;
            return true;
        }
        else {
            return false;
        }
    }

    public List<Pair<Integer, Integer>> getAllTheAvailableCells() {
        List<Pair<Integer,Integer>> listOfAvailableCells = new ArrayList<>();
        for(int i = 0;i<size;i++) {
            for(int j = 0;j<size;j++) {
                if(board[i][j] == null) {
                    listOfAvailableCells.add(new Pair<>(i,j));
                }
            }
        }
        return listOfAvailableCells;
    }

    public void printBoard() {
        for (int i = 0;i<board.length;i++) {
            for (int j =0;j<board.length;j++) {
                if(board[i][j]!=null) {
                    System.out.print(board[i][j].getPieceType().name() + "\t|");
                }
                else {
                    System.out.print("\t|");
                }
            }
            System.out.println();
        }
    }

    public int getSizeOfTheBoard() {
        return this.size;
    }

    public boolean isThereAnyWinner(int row, int col, PieceType pieceType) {
        int count = 0;
        for (int i = 0; i < getSizeOfTheBoard(); i++) {
            if (isCurrentCellNotNull(i,col) && board[i][col].getPieceType() == pieceType) {
                count++;
            }
        }
        if (count == 3) {
            return true;
        }

        count = 0;
        for (int i = 0; i < getSizeOfTheBoard(); i++) {
            if (isCurrentCellNotNull(row,i) && board[row][i].getPieceType() == pieceType) {
                count++;
            }
        }
        if (count == 3) {
            return true;
        }

        count = 0;
        if (row == col) {
            for (int i = 0; i < getSizeOfTheBoard(); i++) {
                if (isCurrentCellNotNull(i,i) && board[i][i].getPieceType() == pieceType) {
                    count++;
                }
            }
            if (count == 3) {
                return true;
            }
        }

        count = 0;
        if (row + col == getSizeOfTheBoard() - 1) {
            for (int i = 0; i < getSizeOfTheBoard(); i++) {
                if (isCurrentCellNotNull(i,getSizeOfTheBoard()-1-i) && board[i][getSizeOfTheBoard() - 1 - i].getPieceType() == pieceType) {
                    count++;
                }
            }
            if (count == 3) {
                return true;
            }
        }
        return false;
    }

    private boolean isCurrentCellNotNull(int row, int col) {
        return board[row][col] != null;
    }

    private boolean validateInputFields(int row,int col) {
        return ((row >= 0 && row < size && col >= 0 && col < size) && board[row][col]==null) ? true : false;
    }
}
