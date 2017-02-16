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
 * This class represents the board that the game takes place on. It is composed
 * of the {@link Square} class and its extended classes, {@link Room} and
 * {@link Hallway}.
 *
 * @author Mora Labisi
 * @author Logan Carichner
 */
public class Board {
	/**
	 * This is a 2D array that represents {@code this} game {@link Board}. It
	 * can be filled with the {@link #fillBoard()} method.
	 */
	private Square[][] grid;
	private int[][] rooms;
	private boolean debug;
	final private int boardSize;

	/**
	 * This is the constructor for the board.
	 */
	public Board() {
		boardSize = 9;
		makeBoard();
		fillBoard();
		makeRooms();
		locateRooms();
		insertBriefcase();
	}

	public Board(int size, boolean debug) {
		boardSize = size;
		this.debug = debug;
		makeBoard();
		fillBoard();
		makeRooms();
		locateRooms();
//		printBoard();
		insertBriefcase();
//		placeEnemies();
//		placePowerUps();
		debugRooms();
	}

	public void makeBoard() {
		grid = new Square[boardSize][boardSize];
	}

	/**
	 * This method will fill the {@link #board} with the appropriate
	 * {@link Square}s.
	 */
	public void fillBoard() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = new Hallway();
			}
		}
	}

	public void makeRooms() {
		int roomsInBoard = getNumRooms();
		for (int i = 1; i < grid.length; i++) {
			for (int j = 1; j < grid[i].length; j++) {
				if (i != 0 && j != 0 && (i+2)%3 == 0 && (j+2)%3 == 0 ) {
					grid[i][j] = new Room();
					grid[i - 1][j].isEntrance();
				}
			}
		}
	}
	
	public void locateRooms() {
		rooms = new int[getNumRooms()][2];
		int temp = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j].getType() == "Room") {
					rooms[temp][0] = i;
					rooms[temp][1] = j;
					temp +=1;
				}
			}
		}
	}

	public void insertBriefcase() {
		int roomNum = Engine.roll(getNumRooms());
		grid[rooms[roomNum][0]][rooms[roomNum][1]].hasBriefcase();
	}
	
	private void placeEnemies() {
		int numEnemies = 2*boardSize/3;
		
	}
	
	private void debugRooms() {
		if (debug) {
			for (int i = 0 ; i < grid.length ; i++) {
				for (int j = 0 ; j < grid[i].length ; j++) {
					grid[i][j].switchLights();
				}
			}
		}
	}

	private int getNumRooms() {
		int num = boardSize / 3;
		return num*num;
	}

	public void printBoardDebugged() {
		for (int i = 0 ; i < grid.length ; i++) {
			for (int j = 0 ; j < grid[i].length ; j++) {
				UI.printString("[" + grid[i][j].getType() + "]");
			}
			UI.printLn();
		}
	}
	
	public void printBoard() {
		for (int i = 0 ; i < grid.length ; i++) {
			for (int j = 0 ; j < grid[i].length ; j++) {
				UI.printString("[" + grid[i][j].getSymbol() + "]");
			}
			UI.printLn();
		}
	}
}
