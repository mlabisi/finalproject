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

/**
 * This class represents the board that the game takes place on. It is
 * composed of the {@link Square} class and its extended classes,
 * {@link Room} and {@link Hallway}.
 *
 * @author
 */
public class Board {
    /**
     * This is a 2D array that represents {@code this} game {@link Board}.
     * It can be filled with the {@link #fillBoard()} method.
     */
    private Square[][] board;

    /**
     * This {@code final int} holds the size of the {@link #board}.
     */
    final public int SIZE;

    /**
     * This is the constructor for the board.
     */
    public Board(){
        SIZE = 9;
    }

    /**
     * This method will fill the {@link #board} with the appropriate
     * {@link Square}s.
     */
    public void fillBoard(){

    }

    /**
     * @return The game {@link #board}
     */
    public Square[][] getBoard(){
        return board;
    }
}
