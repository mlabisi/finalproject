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
	private int turns;
	private int lives;
	private int enemies;
	private int ammo;
	private boolean end;
	
	/**
	 * This method loops the game until
	 * the victory condition is set and 
	 * the player wins.
	 */
	public void LoopGame(){
		
	}
	
	/**
	 * This method increase the amount
	 * of turns and tracks the amount.
	 * @return the turn count.
	 */
	public int addTurn(){
	   return 0;	
	}
	public void countEnemy(){
		
	}
	
	/**
	 * This method changes the game to the
	 * victory condition.
	 * @return the statement to end the game.
	 */
	public boolean endGame(){
		return false;
	}

	public void newPlayer(String name){
		Player player = new Player(name);
	}

}
