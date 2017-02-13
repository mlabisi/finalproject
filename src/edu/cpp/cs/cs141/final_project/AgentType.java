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
 * Created by chocolatecharme on 2/13/17.
 */
public enum AgentType {
    ENEMY("E"),
    PLAYER("P");

    final private String LETTER;

    AgentType(String letter){
        this.LETTER = letter;
    }

    /**
     * @return The letter representation.
     */
    @Override
    public String toString(){
        return LETTER;
    }
}
