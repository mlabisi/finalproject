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

import java.io.Serializable;
import java.util.Arrays;

/**
 * This class represents the board that the game takes place on. It is composed
 * of the {@link Square} class and its extended classes, {@link Room} and
 * {@link Hallway}.
 *
 * @author Mora Labisi
 * @author Logan Carichner
 */
public class Board implements Serializable {
	/**
	 * This is a 2D array that represents {@code this} game {@link Board}. It
	 * can be filled with the {@link #fillBoard()} method.
	 */
	private Square[][] grid;
	private int[][] rooms;
	private int[][] hallways;
	private int[][] ninjas;
	private int[] player;
	private boolean debug;
	private boolean init;
	final private int boardSize;

	/**
	 * This is the default constructor for the board.
	 */
	public Board(boolean debug) {
		boardSize = 9;
		this.debug = debug;
		init = true;
		makeBoard();
		fillBoard();
		makeRooms();
		locateRooms();
		locateHallways();
		insertBriefcase();
		insertEnemies();
		insertPlayer();
		insertItems();
		locateEnemies();
		locatePlayer();
		debugRooms();
		init = false;
	}

	public Board(int size, boolean debug) {
		boardSize = size;
		this.debug = debug;
		init = true;
		makeBoard();
		fillBoard();
		makeRooms();
		locateRooms();
		locateHallways();
		insertBriefcase();
		insertEnemies();
		insertPlayer();
		insertItems();
		locateEnemies();
		locatePlayer();
		debugRooms();
		init = false;
	}
	
	private void locatePlayer() {
		// TODO Auto-generated method stub
		
	}

	private void insertItems() {
		// TODO Auto-generated method stub
		
	}

	private void insertPlayer() {
		grid[boardSize - 1][0].placeSpy();
	}

	public void killNinja() {
		grid[ninjas[1][0]][ninjas[1][1]].killAgent();
		locateEnemies();
	}

	/**
	 * This method initiates the board as an array of square objects.
	 */
	public void makeBoard() {
		grid = new Square[boardSize][boardSize];
	}

	/**
	 * This method makes every single square into a hallway object, which can
	 * hold players, enemies, or items
	 */
	public void fillBoard() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = new Hallway();
			}
		}
		if (grid.length > 3)
			assignOffLimits();
	}

	/**
	 * This method sets the spaces closest to the player spawn point as off
	 * limits to enemy placement
	 */
	private void assignOffLimits() {
		int temp = 3;
		while (temp > 0) {
			grid[grid.length - temp][0].restrict();
			grid[grid.length - 1][temp].restrict();
			temp -= 1;
		}
	}

	/**
	 * This method replaces the hallway object with a room object inside each
	 * 3x3 square section of the grid
	 */
	public void makeRooms() {
		int roomsInBoard = getNumRooms();
		for (int i = 1; i < grid.length; i++) {
			for (int j = 1; j < grid[i].length; j++) {
				if ((i + 2) % 3 == 0 && (j + 2) % 3 == 0) {
					grid[i][j] = new Room();
					grid[i - 1][j].isEntrance();
				}
			}
		}
	}

	/**
	 * This method creates an array containing the location of each room object
	 * in the grid.
	 */
	public void locateRooms() {
		rooms = new int[getNumRooms()][2];
		int temp = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j].getType() == "Room") {
					rooms[temp][0] = i;
					rooms[temp][1] = j;
					temp += 1;
				}
			}
		}
	}

	/**
	 * This method creates an array containing the location of each hallway
	 * object in the grid.
	 */
	public void locateHallways() {
		hallways = new int[boardSize * boardSize - getNumRooms()][2];
		int temp = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j].getType() != "Room") {
					hallways[temp][0] = i;
					hallways[temp][1] = j;
					temp += 1;
				}
			}
		}
	}

	/**
	 * This method places the briefcase object within one of the rooms at
	 * random.
	 */
	public void insertBriefcase() {
		int roomNum = Engine.roll(rooms.length);
		grid[rooms[roomNum][0]][rooms[roomNum][1]].hasBriefcase();
	}

	/**
	 * This method places a number of ninja assassin objects into the grid based
	 * on the size of the grid
	 */
	private void insertEnemies() {
		int numEnemies = 2 * boardSize / 3;
		while (numEnemies > 0) {
			int hallwayNum = Engine.roll(hallways.length);
			if (!grid[hallways[hallwayNum][0]][hallways[hallwayNum][1]].hasAgent()) {
				if (!grid[hallways[hallwayNum][0]][hallways[hallwayNum][1]].isOffLimits()) {
					grid[hallways[hallwayNum][0]][hallways[hallwayNum][1]].placeAgent();
					numEnemies -= 1;
				}
			}
		}
	}

	/**
	 * This method creates an array containing the locations of each ninja
	 * assassin in the grid
	 */
	private void locateEnemies() {
		int numEnemies = 0;
		if (init) 
			numEnemies = 2 * boardSize / 3;
		else 
			numEnemies = ninjas.length - 1;
		int temp = 0;
		ninjas = new int[numEnemies][2];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j].hasAgent() && temp < ninjas.length) {
					ninjas[temp][0] = i;
					ninjas[temp][1] = j;
					temp++;
				}
			}
		}
	}

	/**
	 * This method checks to see if debug mode is on, then turns on the lights
	 * in every room if it is.
	 */
	public void debugRooms() {
		if (debug) {
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[i].length; j++) {
					grid[i][j].switchLights();
				}
			}
		}
	}

	/**
	 * This method gets the number of rooms for the grid based on the size of
	 * the grid.
	 * 
	 * @return
	 */
	public int getNumRooms() {
		int num = boardSize / 3;
		return num * num;
	}

	/**
	 * This method calls the UI to print out the entire board in its current
	 * state.
	 */
	public void printBoard() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				UI.printString("[" + grid[i][j].getSymbol() + "]");
			}
			UI.printLn();
		}
	}

	/**
	 * This method moves each ninja's location.
	 */
	public void moveNinjas() {
		boolean validDirection = false;
		int direction = 0;
		for (int i = 0; i < ninjas.length; i++) {
			do {
				direction = grid[ninjas[i][0]][ninjas[i][1]].askANinja();
				validDirection = checkValidDirection(direction, i);
			} while (validDirection == false); //while (i < grid.length && j < grid[i].length && hasenemy == false || isRoom == false);
			// UI.printString(Arrays.toString(ninjas[i])); //Debug
			moveAgent("ninjas", i, direction);
		}
	}

	/**
	 * This method moves a single ninja to the location its AI has requested.
	 * 
	 * @param ninja
	 *            The integer location of this ninja within the ninjas array
	 * @param direction
	 *            The integer value representing one of four directions for the
	 *            ninja to move
	 */
	private void moveAgent(String agent, int ninja, int direction) {
		int ninjaX = 0;
		int ninjaY = 0;
		if (agent == "ninjas") {
			ninjaX = ninjas[ninja][1];
			ninjaY = ninjas[ninja][0];
		}else if (agent == "player") {
			ninjaX = player[1];
			ninjaY = player[0];
		}else UI.callException("agent");
		ActiveAgent tempNinja = null;
		tempNinja = grid[ninjaY][ninjaX].getAgent();
		grid[ninjaY][ninjaX].deleteAgent();
		switch (direction) {
		case 0: // Up
			grid[ninjaY - 1][ninjaX].placeAgent(tempNinja);
			ninjas[ninja][0] -= 1;
			break;
		case 1: // Left
			grid[ninjaY][ninjaX - 1].placeAgent(tempNinja);
			ninjas[ninja][1] -= 1;
			break;
		case 2: // Down
			grid[ninjaY + 1][ninjaX].placeAgent(tempNinja);
			ninjas[ninja][0] += 1;
			break;
		case 3: // Right
			grid[ninjaY][ninjaX + 1].placeAgent(tempNinja);
			ninjas[ninja][1] += 1;
			break;
		default:
			UI.callException("ToDo");
		}
	}

	/**
	 * This method checks to see if the ninja can move in the direction its AI
	 * has requested.
	 * 
	 * @param direction
	 *            The integer value representing the direction the ninja wants
	 *            to move.
	 * @param ninja
	 *            The integer value representing the ninja in the ninjas array.
	 * @return True if the ninja can move in the requested direction, else
	 *         false.
	 */
	private boolean checkValidDirection(int direction, int ninja) {
		int ninjaX = ninjas[ninja][1];
		int ninjaY = ninjas[ninja][0];
		switch (direction) {
		case 0: // Up
			if (ninjaY - 1 < 0)
				return false;
			return grid[ninjaY - 1][ninjaX].checkIsClear();
		case 1: // Left
			if (ninjaX - 1 < 0)
				return false;
			return grid[ninjaY][ninjaX - 1].checkIsClear();
		case 2: // Down
			if (ninjaY + 1 >= grid.length)
				return false;
			return grid[ninjaY + 1][ninjaX].checkIsClear();
		case 3: // Right
			if (ninjaX + 1 >= grid.length)
				return false;
			return grid[ninjaY][ninjaX + 1].checkIsClear();
		default:
			return false;
		}
	}

	public void playerMove(int playerMovement) {
		// TODO Auto-generated method stub
		
	}
}
