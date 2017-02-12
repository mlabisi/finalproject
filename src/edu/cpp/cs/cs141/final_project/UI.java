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
 * @author Logan Carichner
 */
public class UI {
	/**
	 * This class is the UI for the game, it will create a new game engine instance and then tell the game to make the player.
	 *
	 * @author Logan Carichner
	 */
	private Engine game;

	/**
	 * This constructor builds the UI and starts the game with the engine.
	 */
	public UI() {
		game = new Engine();
		String playerName = getPlayerName();
		game.newPlayer(playerName);
		game.newGame();
	}
	
	/**
	 * This method will get the player's name and return it.
	 */
	public String getPlayerName() {
		return null;
	}

	/**
	 * This method can be called by other classes to print to the console.
	 * @param string the string to be printed
	 */
	public void printString(String string) {
		
	}
	
	/**
	 * This method runs the first menu of the game and gives the player options to start the game or load a saved game.
	 */
	public void mainMenu() {
		
	}
	
	/**
	 * This method starts the game and initiates the engine's creation of enemies.
	 */
	public void gameStart() {
		runGame();
	}
	
	/**
	 * For the duration of the game, this class runs the player and enemy turns.
	 */
	private void runGame() {
		
	}

	/**
	 * This method gives the player options to take on their turn.
	 */
	public void playerturn() {
		
	}
	
	/**
	 * This method tells the engine to run the enemy's turn.
	 */
	public void enemyTurn() {
		game.enemyTurn();
	}

}
