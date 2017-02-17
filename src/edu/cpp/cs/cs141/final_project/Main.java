/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodr�guez
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
 * This class is the main class, and runs the game when executed
 *
 * @author Logan Carichner
 * @author Mora Labisi
 */
public class Main {

	public static void main(String[] args){
	    Engine game = new Engine();
        UI ui = new UI(game);
		ui.gameStart();
	}

}
