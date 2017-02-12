/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodríguez
 *
 * Final Project: Spy Game
 *
 * Create a small, yet interesting, text-based game involving a spy
 * and ninjas.
 *
 * Team Magic!~<3
 * Diana Choi, William Hang, Logan Carichner, Robert Delfin, Mora Labisi
 */
package edu.cpp.cs.cs141.final_project.Mora;

import edu.cpp.cs.cs141.final_project.Hallway;
import edu.cpp.cs.cs141.final_project.Room;
import edu.cpp.cs.cs141.final_project.Square;

import java.util.List;

/**
 * This class represents the board that the game takes place on. It is
 * composed of the {@link Square} class and its extended classes,
 * {@link Room} and {@link Hallway}.
 *
 * @author Mora Labisi
 */
public class Board {
    /**
     * This is a 2D array that represents {@code this} game {@link Board}.
     * It can be filled with the {@link #fillBoard()} method.
     */
    private Square[][] board;

    private boolean debugMode;

    /**
     * This {@code final int} holds the size of the {@link #board}.
     */
    final public int SIZE;

    /**
     * This is the constructor for the board.
     */
    public Board(){
        SIZE = 9;
        board = new Square[SIZE][SIZE];
        fillBoard();
    }

    /**
     * This method will fill the {@link #board} with the appropriate
     * {@link Square}s.
     */
    public void fillBoard(){
        for (int i = 0; i < SIZE; ++i){
            for (int j = 0; j < SIZE; ++j){
                if((i == 1 || i == 4 || i == 7) && (j == 1 || j == 4 || j == 7))
                    board[i][j] = new Room();
                else
                    board[i][j] = new Hallway();
            }
        }
    }

    /**
     * @return The game {@link #board}
     */
    public String toString() {
        String str = "";
        int i = 0;
        for (Square[] row : board) {
            for (Square column : row) {
                if(i == 9){
                    str += "\n";
                    i = 0;
                }
                str += "[ " + column.toString() + " ] ";
                ++i;
            }
        }
        return str;
    }

    public void setDebugMode(boolean value){
        debugMode = value;
    }
}
