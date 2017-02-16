package edu.cpp.cs.cs141.final_project;

import java.util.Scanner;

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
		game.printBoard();
//		game.printBoardDebug();
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
	public static void printString(String string) {
		System.out.print(string);
	}
	
	public static void printLn() {
		System.out.println();
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

	public static int getBoardSize() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Boardsize?");
		int num = sc.nextInt();
		return num;
	}

	public static boolean debugStart() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Debug mode? 1 for yes. 0 for no.");
		int num = sc.nextInt();
		return (num == 0) ? false : true;
	}

	public static void callException(String exception) {
		switch (exception) {
		case "Motion":
			System.out.println("An error occured when an entity tried to move in an undefined manner.");
			exit();
			break;
//		case "
			
		}
		
	}

	private static void exit() {
		System.exit(0);		
	}

}
