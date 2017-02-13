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
package edu.cpp.cs.cs141.final_project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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

    private Random rand;

    private boolean debugMode = false;


    private ArrayList<Integer> rows;

    private ArrayList<Integer> columns;

    /**
     * This {@code final int} holds the size of the {@link #board}.
     */
    final public int SIZE;

    /**
     * This is the constructor for the board.
     */
    public Board(){
        rand = new Random();
        SIZE = 9;
        board = new Square[SIZE][SIZE];

        for(int i = 0; i != 2 && i != 4 && i != 7; ++i)
            rows.add(i);

        for(int i = 0; i != 2 && i != 4 && i != 7; ++i)
            columns.add(i);

        fillBoard();
        placeBriefcase();

        toggleDebugMode();
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

    public void placeBriefcase(){
        switch(rand.nextInt(9)){
            case 0: ((Room)board[1][1]).setHasBriefcase(true);
                    break;
            case 1: ((Room)board[1][4]).setHasBriefcase(true);
                    break;
            case 2: ((Room)board[1][7]).setHasBriefcase(true);
                    break;
            case 3: ((Room)board[4][1]).setHasBriefcase(true);
                    break;
            case 4: ((Room)board[4][4]).setHasBriefcase(true);
                    break;
            case 5: ((Room)board[4][7]).setHasBriefcase(true);
                    break;
            case 6: ((Room)board[7][1]).setHasBriefcase(true);
                    break;
            case 7: ((Room)board[7][4]).setHasBriefcase(true);
                    break;
            case 8: ((Room)board[7][7]).setHasBriefcase(true);
        }
    }

    public void placeItems(){
        Collections.shuffle(rows);
        Collections.shuffle(columns);

    }

    /**
     * @return The game {@link #board}
     */
    public String toString() {
        String str = "";
        int i = 0;
        for (Square[] row : board) {
            for (Square column : row) {
                if(debugMode) {
                    if (i == 9) {
                        str += "\n";
                        i = 0;
                    }
                    if(column.getType() == SquareType.ROOM){
                        if(((Room) column).checkHasBriefcase())
                            str += "[ " + column.reveal() + "] ";
                        else
                            str += "[ " + column.reveal() + " ] ";
                    }else
                        str += "[ " + column.reveal() + " ] ";
                    ++i;
                } else {
                    if (i == 9) {
                        str += "\n";
                        i = 0;
                    }
                    str += "[ " + column + " ]";
                    ++i;
                }
            }
        }
        return str;
    }

    /**
     * Toggles debug mode
     */
    public void toggleDebugMode(){
        debugMode = !debugMode;
    }

    /**
     * @return The {@code boolean} value of {@link #debugMode}
     */
    public boolean checkDebugMode(){
        return debugMode;
    }
}