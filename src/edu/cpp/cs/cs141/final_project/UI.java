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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;


/**
 * This class is the UI for the game, it will create a new game engine instance and then tell the game to make the player.
 *
 * @author Logan Carichner
 * @author Robert Delfin
 */
public class UI {
	private Engine game;
	private Scanner sc;
	private boolean started = false;

	/**
	 * This constructor builds the UI and starts the game with the engine.
	 */
	public UI() {
		sc = new Scanner(System.in);
//		game = new Engine();		//debug
		game = new Engine(9);
		mainMenu(true);
//		debugLoop();		//debug
	}
	
	/**
	 * This debugLoop is used for testing purposes and moves the ninjas
     * 250 times.
	 */
	public void debugLoop() {
		for (int i = 250 ; i > 0 ; i--) {
			game.enemyTurn();
			dialogueWait(2);
			printLn();
			game.printBoard();
		}
	}
	

	/**
     * This method is used to create a delay for the display.
     *
	 * @param i
	 */
	public static void dialogueWait(int i) {
		for ( ; i > 0 ; i--) {
			try {
				Thread.sleep(125);		//default 150
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
	

	/**
	 * This method can be called by other classes to print a line break.
	 */
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
			started = true;
			newGame();
			break;
		case 2:
			gameHelp(-1);
			break;
		case 3:
			loadGame();
			runGame();
			break;
		case 4:
			System.out.println("Goodbye!");
			game.quit();
			break;
		default:
			System.out.println("Please enter a valid choice.");
			dialogueWait(1);
			mainMenu(false);
			break;
		}
	}
	

	/**
	 * This method is called when a new game is started.
	 */
	private void newGame() {
		System.out.println("The game begins...");
		dialogueWait(10);
		runGame();
	}


    /**
     * This method runs the help menu.
     *
	 * @param section The section of the help menu to display
	 */
	private void gameHelp(int section) {
		printLn();
		boolean end = false;
		switch (section) {
		case 1:
			System.out.println("+---------------------------------GAME HELP---------------------------------+");
			System.out.println("+---------------------------------THE STORY---------------------------------+");
			dialogueWait(2);
			System.out.println("|   You are a secret agent working for the world's most elite spy agency.   |");
			dialogueWait(2);
			System.out.println("|    Your mission is to retrieve the intelligence from one of the rooms     |");
			System.out.println("|                           within this building.                           |");
			dialogueWait(2);
			System.out.println("|         You have several tools at your disposal on this mission:          |");
			dialogueWait(2);
			System.out.println("|       - (1) TeleDart RD206 Pistol   |- (1) AN/PVS-7A Night Vision Goggles |");
			dialogueWait(2);
			System.out.println("|       - (1) TeleDart 98X.2 Dart     |- (2) PX1 Instant-Health kits        |");
			dialogueWait(2);
			break;
		case 2:
			System.out.println("+---------------------------------GAME HELP---------------------------------+");
			System.out.println("+---------------------------------YOUR TURN---------------------------------+");
			dialogueWait(2);
			System.out.println("|   On your turn, you should 'Look around' with your night vision goggles.  |");
			dialogueWait(2);
			System.out.println("|After surveying your surroundings you can 'move' or 'shoot' in a direction.|");
			dialogueWait(2);
			System.out.println("|Your Dart Gun can shoot the entire length of the building. If you choose to|");
			System.out.println("|     shoot, it will hit the first enemy it reaches in a straight line.     |");
			dialogueWait(2);
			System.out.println("|        You can move one space in whatever direction you choose to.        |");
			dialogueWait(2);
			System.out.println("| If you are north of a room, you can move down to access the room and look |");
			System.out.println("|                      for the intelligence briefcase.                      |");
			dialogueWait(2);
			break;
		case 3:
			System.out.println("+---------------------------------GAME HELP---------------------------------+");
			System.out.println("|                   Pick up the briefcase to win the game.                  |");
			break;
		case 4:
			if (!started)
				mainMenu(false);
			else 
				playerTurn(false);
			end = true;				
			break;
		default:
			System.out.println("+---------------------------------GAME HELP---------------------------------+");
			System.out.println("|                     What area would you like help in?                     |");
			dialogueWait(2);
			System.out.println("|                     1. The Story  | 3. Winning Game                       |");
			dialogueWait(2);
			System.out.println("|                     2. Your Turn  | 4. Return to Game                     |");
			dialogueWait(2);
			break;
		}
		if (!end) {
		System.out.println("+---------------------------------------------------------------------------+");
		dialogueWait(16);
			if (section < 0)
				section = sc.nextInt();
			else 
				gameHelp(-1);
			gameHelp(section);
		}
	}

	/**
	 * For the duration of the game, this class runs the player and enemy turns.
	 */
	private void runGame() {
		do {
			game.printBoard();
			playerTurn(false);
			dialogueWait(8);
			int playerHP = checkPlayerHP();
			game.enemyTurn();
			checkPlayerHurt(playerHP);
		} while (game.getState() == false);
	}

	/**
	 * @return The amount of lives the player has
	 */
	public int checkPlayerHP() {
		return game.getLives();
	}
	
	/**
     * Displays the appropriate message based on the player's HP.
     *
	 * @param HP
	 */
	public void checkPlayerHurt(int HP) {
		if (game.getLives() < HP) {
			if (game.getLives() != 0) {
				System.out.println("You crawl back to the starting position to nurse your wounds, ");
				System.out.println("and use up one of your health kits in the process!");
			} else {
				System.out.println("All out of health kits, you are unable to stop the bleeding from your fresh wound!");
				dialogueWait(6);
				System.out.println("You collapse into a pool of your own blood, your body going still as you bleed out...");
				dialogueWait(6);
				gameOver();
			}
			dialogueWait(8);
		}
	}
	
	/**
	 * Displays the appropriate message when a ninja the player.
	 */
	public static void stab() {
		System.out.println("Ow! Something stabbed you in the darkness!");
		dialogueWait(8);
	}
	
	/**
	 * Displays the appropriate message and quits the game.
	 */
	public void gameOver() {
		System.out.println("You died before finding the briefcase.");
		dialogueWait(6);
		System.out.println("Game Over");
		game.quit();
	}

	/**
	 * This method gives the player options to take on their turn.
	 */
	public void playerTurn(boolean looked) {
		System.out.println("It is your turn, what action will you take?");
		int choice = 0;
		if (looked && game.checkEntrance()) {
			System.out.println("1. Move     |// Look around    |7. Access intel");
			System.out.println("2. Shoot    |5. Save game      |0. Quit");
			System.out.println("3. Help     |6. Debug mode: " + game.getDebug());
			System.out.println("Lives: " + game.getLives() + "    |Ammo: " + game.getPlayerAmmo());
		}else if (looked) {
			System.out.println("1. Move     |// Look around    |0. Quit");
			System.out.println("2. Shoot    |5. Save game");
			System.out.println("3. Help     |6. Debug mode: " + game.getDebug());
			System.out.println("Lives: " + game.getLives() + "    |Ammo: " + game.getPlayerAmmo());
		}else {
			System.out.println("// Move     |4. Look around    |0. Quit");
			System.out.println("// Shoot    |5. Save game");
			System.out.println("3. Help     |6. Debug mode: " + game.getDebug());
			System.out.println("Lives: " + game.getLives() + "    |Ammo: " + game.getPlayerAmmo());
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
				System.out.println("You should look around before you shoot.");
				printLn();
				dialogueWait(8);
				playerTurn(false);
			} else
				playerShoot();
			break;
		case 3:
			gameHelp(-1);
			break;
		case 4:
			if (looked) {
				System.out.println("You've already looked around this turn.");
				printLn();
				dialogueWait(8);
			} else 
				playerLook();
			game.printBoard();
			playerTurn(true);
			break;
		case 5:
			saveGame();
			break;
		case 6:
			game.toggleDebug();
			game.printBoard();
			if (looked) playerTurn(true);
			else playerTurn(false);
			break;
		case 7:
			if (game.checkEntrance()) {
				checkIntel();
		 		break;
		    }
		case 0:
            System.out.println("Goodbye!");
            game.quit();
		default:
			System.out.println("Please enter a valid choice.");
			dialogueWait(2);
			if (looked) playerTurn(true);
			else playerTurn(false);
			break;
		}
	}
	
	/**
	 * This method displays the appropriate message after the user checks a room.
	 */
	private void checkIntel() {
		if(game.checkCaseRoom()){
			System.out.println("Congratulations, you have found the briefcase!");
			exit();
		}else{
			System.out.println("This room does not contain the briefcase you are looking for.");
		}
	}
	
	/**
	 * This method runs the save game dialogue.
	 */
	private void saveGame() {
		Scanner kb = new Scanner(System.in);
		String Filename;
		System.out.println("Type a name of the file you want to save add .ser at the end");
		Filename = kb.nextLine();
		System.out.println("-----Saving Game-----");
		
		try{
			FileOutputStream outFile = new FileOutputStream(Filename);
			ObjectOutputStream out = new ObjectOutputStream(outFile);
			
			out.writeObject(game);
			
			out.close();
			outFile.close();
			dialogueWait(8);
			System.out.println();
			System.out.println("-----Game State Saved-----");
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This method runs the load game dialogue.
	 */
	private void loadGame() {
		Scanner kb = new Scanner(System.in);
		String Filename;
		System.out.println("Type a name of the file you want to load add .ser at the end");
		Filename = kb.nextLine();
		System.out.println("-----Loading Game-----");
		System.out.println("");
		
		try{
			System.out.println("Creating File input Stream");
			
			FileInputStream fileIn = new FileInputStream(Filename);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			System.out.println("Loading Engine");
			game =(Engine)in.readObject();
			
			in.close();
			fileIn.close();
			dialogueWait(8);
			System.out.println("-----File Loaded-----");
			
		}catch(ClassNotFoundException e){
		  e.printStackTrace();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This method runs the move player dialogue.
	 */
	private void playerMove() {
		System.out.println("Please choose a direction to move in.");
		int choice = 0;
		System.out.println("1. Up    |3. Right");
		System.out.println("2. Down  |4. Left");
		choice = sc.nextInt();
		sc.nextLine();
		switch (choice) {
		case 1: 
			if (game.checkPlayerMove(0))
				game.movePlayer(0);
			else {
				System.out.println("You cannot move in that direction.");
				dialogueWait(2);
				playerMove();
			}
			break;
		case 2:
			if (game.checkPlayerMove(2))
				game.movePlayer(2);
			else {
				System.out.println("You cannot move in that direction.");
				dialogueWait(2);
				playerMove();
			}
			break;
		case 3:
			if (game.checkPlayerMove(3))
				game.movePlayer(3);
			else {
				System.out.println("You cannot move in that direction.");
				dialogueWait(2);
				playerMove();
			}
			break;
		case 4:
			if (game.checkPlayerMove(1))
				game.movePlayer(1);
			else {
				System.out.println("You cannot move in that direction.");
				dialogueWait(2);
				playerMove();
			}
			break;
		default:
			System.out.println("That is not a valid choice.");
			dialogueWait(2);
			playerMove();
			break;
		}
	}

	/**
	 * This method runs the player shoot dialogue.
	 */
	private void playerShoot() {
		System.out.println("Please choose a direction to shoot. ");
		int choice = 0;
		System.out.println("1. Up    |3. Right");
		System.out.println("2. Down  |4. Left");
		choice = sc.nextInt();
		sc.nextLine();
		switch(choice){
			case 1:
				if(game.checkGun())
					game.shootGun(0);
				else
					System.out.println("You have no ammo!!");
				break;
			case 2:
				if(game.checkGun())
					game.shootGun(1);
				else
					System.out.println("You have no ammo!");
				break;
			case 3:
				if(game.checkGun())
					game.shootGun(2);
				else
					System.out.println("You have no ammo!");
				break;
			case 4:
				if(game.checkGun())
					game.shootGun(3);
				else
					System.out.println("You have no ammo!");
				break;
			default:
				System.out.println("That is not a valid choice.");
				dialogueWait(2);
				playerShoot();
				break;
		}
	}
	
	/**
	 * This method runs the player look dialogue.
	 */
	private void playerLook() {
		printLn();
		dialogueWait(8);
		System.out.println("Please choose a direction to look in.");
		int choice = 0;
		System.out.println("1. Up    |3. Right");
		System.out.println("2. Down  |4. Left");
		choice = sc.nextInt();
		sc.nextLine();
		switch(choice) {
			case 1:
				game.lookInDir(0);
				break;
			case 2:
				game.lookInDir(2);
				break;
			case 3:
				game.lookInDir(3);
				break;
			case 4:
				game.lookInDir(1);
				break;
			default:
				System.out.println("Invalid input. Please try again.");
				playerLook();
				break;
		}
		printLn();
		dialogueWait(8);
	}
	
	/**
	 * This method tells the engine to run the enemy's turn.
	 */
	public void enemyTurn() {
		game.enemyTurn();
	}

	/**
	 * @return The size of the board chosen by the player.
	 */
	public static int getBoardSize() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Boardsize?");
		int num = sc.nextInt();
		return num;
	}

	/**
	 * @return The boolean value for debug mode
	 */
	public static boolean debugStart() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Debug mode? 1 for yes. 0 for no.");
		int num = sc.nextInt();
		return (num == 0) ? false : true;
	}

	/**
     * A custom exception
     *
	 * @param exception
	 */
	public static void callException(String exception) {
		switch (exception) {
		case "Motion":
			System.out.println("An error occured when an entity tried to move in an undefined manner.");
			exit();
			break;			
		}
	}

	/**
	 * Exits the game.
	 */
	private static void exit() {
		System.exit(0);		
	}
}