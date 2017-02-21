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
	private Scanner sc;

	/**
	 * This constructor builds the UI and starts the game with the engine.
	 */
	public UI() {
		sc = new Scanner(System.in);
		game = new Engine();
		mainMenu(true);
		game.printBoard();	//debug
		debugLoop();		//debug
	}
	
	public void debugLoop() {
		for (int i = 250 ; i > 0 ; i--) {
			game.enemyTurn();
			game.killNinja();
			dialogueWait(1);
			System.out.println();
			game.printBoard();
		}
	}
	
	public static void dialogueWait(int i) {
		for ( ; i > 0 ; i--) {
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
			}
		}
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
	public void mainMenu(boolean intro) {
		int choice = 0;
		if (intro) {
			System.out.println("Welcome to Team Magic!~<3's Ninja Assassin game!");
			dialogueWait(3);
		}
		System.out.println("Please choose an option from the list below.");
		dialogueWait(2);
		System.out.println("1. New Game  | 3. Load a saved game");
		System.out.println("2. Get Help  | 4. Quit");
		choice = sc.nextInt();
		sc.nextLine();
		switch (choice) {
		case 1:
			newGame();
			break;
		case 2:
			gameHelp();
			break;
		case 3:
			loadGame();
			break;
		case 4:
			game.quit();
			break;
		default:
			System.out.println("Please enter a valid choice.");
			dialogueWait(1);
			mainMenu(false);
			break;
		}
	}
	
	private void newGame() {
		System.out.println("The game begins...");
		dialogueWait(3);
		System.out.println("You are a secret agent working for the world's most elite spy agency...");
		dialogueWait(2);
		System.out.println("Your mission is to retrieve the intelligence from one of the " + game.getNumRooms() + " rooms within this building.");
		dialogueWait(2);
		System.out.println("You have several tools at your disposal on this mission:");
		dialogueWait(1);
		System.out.println();
		System.out.println("- (1) TeleDart RD206 Pistol");
		dialogueWait(1);
		System.out.println("- (1) TeleDart 98X.2 Toxic Dart");
		dialogueWait(1);
		System.out.println("- (1) AN/PVS-7A Night Vision Goggles");
		dialogueWait(1);
		System.out.println("- (2) PX1 Instant-Health kits");
		dialogueWait(2);
		runGame();
	}

	private void gameHelp() {
		// TODO Auto-generated method stub
		
	}

	private void loadGame() {
		System.out.println("-----Load Game-----");
		System.out.println("");
		// TODO Auto-generated method stub
	}

	/**
	 * For the duration of the game, this class runs the player and enemy turns.
	 */
	private void runGame() {
		do {
			playerTurn(false);
			game.enemyTurn();
		} while (game.getState() == false);
	}

	/**
	 * This method gives the player options to take on their turn.
	 */
	public void playerTurn(boolean looked) {
		System.out.println("It is your turn, what action will you take?");
		int choice = 0;
		if (looked) {
			System.out.println("1. Move");
			System.out.println("2. Shoot");
		}else {
			System.out.println("// 1. Move");
			System.out.println("// 2. Shoot");
			System.out.println("3. Look around");
		}
		choice = sc.nextInt();
		sc.nextLine();
		switch (choice) {
		case 1:
			playerMove();
			break;
		case 2:
			playerShoot();
			break;
		case 3:
			if (looked) {
				System.out.println("You've already looked around.");
				printLn();
				dialogueWait(2);
				playerTurn(true);
			} else playerLook();
			break;
		default:
			System.out.println("Please enter a valid choice.");
			dialogueWait(2);
			if (looked) playerTurn(true);
			else playerTurn(false);
			break;
		}
	}
	
	private void playerMove() {
		
	}
	
	private void playerShoot() {
		
	}
	
	private void playerLook() {
		
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
		}
	}

	private static void exit() {
		System.exit(0);		
	}

	public static int getPlayerMovement() {
		// TODO Auto-generated method stub
		return 0;
	}

}
