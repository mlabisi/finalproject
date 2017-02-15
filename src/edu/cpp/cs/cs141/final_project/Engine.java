package edu.cpp.cs.cs141.final_project;

/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodr�guez
 *
 * Programming Assignment Final Project
 *
 * To make a game that has a spy that has
 * to find a briefcase in a building full
 * of ninjas.
 *
 * Team Magic!~<3
 *   Robert Delfin
 *   William Hang
 *   Diana Choi
 *   Logan Carichner
 *   Mora Labisi
 */

/**
 * @author Robert Delfin
 *
 */
public class Engine {

    private Player player;

    private Board board;

    public Engine(){
	    Player player = new Player();
	    Board board = new Board();
    }

    public Player getPlayer(){
	    return player;
    }

	//some type of method that manages the invincibility
    // if isInvinvible, toggleIsInvincible when the difference
    // between current turns and previous turns is 5

}
