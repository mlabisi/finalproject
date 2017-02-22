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
//		debugLoop();		//debug
	}
	
	public void debugLoop() {
		for (int i = 250 ; i > 0 ; i--) {
			game.enemyTurn();
			game.killNinja();
			dialogueWait(1);
			printLn();
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
			gameHelp(0);
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
		dialogueWait(10);
		runGame();
	}

	private void gameHelp(int section) {
			System.out.println("+---------------------------------GAME HELP---------------------------------+");
		switch (section) {
		case 0:
			System.out.println("+---------------------------------THE STORY---------------------------------+");
			dialogueWait(4);
			System.out.println("|   You are a secret agent working for the world's most elite spy agency.   |");
			dialogueWait(4);
			System.out.println("|    Your mission is to retrieve the intelligence from one of the rooms     |");
			System.out.println("|                           within this building.                           |");
			dialogueWait(4);
			System.out.println("|         You have several tools at your disposal on this mission:          |");
			dialogueWait(4);
			System.out.println("|       - (1) TeleDart RD206 Pistol   |- (1) AN/PVS-7A Night Vision Goggles |");
			dialogueWait(4);
			System.out.println("|       - (1) TeleDart 98X.2 Dart     |- (2) PX1 Instant-Health kits        |");
			dialogueWait(4);
			break;
		case 1:
			System.out.println("+---------------------------------YOUR TURN---------------------------------+");
			dialogueWait(4);
			System.out.println("|   On your turn, you should 'Look around' with your night vision goggles.  |");
			dialogueWait(4);
			System.out.println("|After surveying your surroundings you can 'move' or 'shoot' in a direction.|");
			dialogueWait(4);
			System.out.println("|Your Dart Gun can shoot the entire length of the building. If you choose to|");
			System.out.println("|     shoot, it will hit the first enemy it reaches in a straight line.     |");
			dialogueWait(4);
			System.out.println("|        You can move one space in whatever direction you choose to.        |");
			dialogueWait(4);
			System.out.println("| If you are north of a room, you can move down to access the room and look |");
			System.out.println("|                      for the intelligence briefcase.                      |");
			dialogueWait(4);
			break;
		case 3:
			
		default:
			System.out.println("|                     What area would you like help in?                     |");
			dialogueWait(4);
			System.out.println("|                     1. The Story  | 3. Winning Game                       |");
			dialogueWait(4);
			System.out.println("|                     2. Your Turn  | 4. Return to Game                     |");
			dialogueWait(4);
		}
		System.out.println("+---------------------------------------------------------------------------+");
		dialogueWait(8);
		if (section < 0)
			section = sc.nextInt();
		else 
			gameHelp(-1);
		printLn();
		
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
			game.printBoard();
			playerTurn(false);
			System.out.println("You can hear a shuffling in the darkness, as the ninja assassins hunting you move about...");
			dialogueWait(8);
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
			System.out.println("1. Move     |// Look around");
			System.out.println("2. Shoot    |4. Save game");
			System.out.println("5. Help     |6. Debug mode: " + game.getDebug());
		}else {
			System.out.println("// Move     |3. Look around");
			System.out.println("// Shoot    |4. Save game");
			System.out.println("5. Help     |6. Debug mode: " + game.getDebug());
		}
		choice = sc.nextInt();
		sc.nextLine();
		switch (choice) {
		case 1:
			if (!looked) {
				System.out.println("You should look around before you move.");
				printLn();
				dialogueWait(8);
				playerTurn(false);
			} else
				playerMove();
			break;
		case 2:
			if (!looked) {
				System.out.println("You should look around before you move.");
				printLn();
				dialogueWait(8);
				playerTurn(false);
			} else
				playerShoot();
			break;
		case 3:
			if (looked) {
				System.out.println("You've already looked around this turn.");
				printLn();
				dialogueWait(8);
				playerTurn(true);
			} else 
				playerLook();
			break;
		case 4:
			saveGame();
			break;
		case 5:
			gameHelp(0);
		case 6:
			game.toggleDebug();
			game.printBoard();
			if (looked) playerTurn(true);
			else playerTurn(false);
			break;
		default:
			System.out.println("Please enter a valid choice.");
			dialogueWait(2);
			if (looked) playerTurn(true);
			else playerTurn(false);
			break;
		}
	}
	
	private void saveGame() {
		//TODO
	}
	
	private void playerMove() {
		
	}
	
	private void playerShoot() {
		
	}
	
	private void playerLook() {
		printLn();
		dialogueWait(8);
		
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
