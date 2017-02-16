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
 * This enum type will represent the specific type of squares that
 * exist in the spy game.
 *
 * @author Mora Labisi
 */
public enum SquareType{
    HALLWAY("[ \u2008 ]"),
    ROOM("[ \uD83D\uDEAA ]");

    private String letter;

    /**
     * Sets the character representation for each square type.
     *
     * @param letter The value of letter as a char
     */
    SquareType(String letter){
        this.letter = letter;
    }

    /**
     * @return The {@code char} representation of the square
     */
    @Override
    public String toString(){
        return letter;
    }
}





