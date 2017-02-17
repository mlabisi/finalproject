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
		locateHallways();
		insertBriefcase();
		insertEnemies();
		locateEnemies();
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
		if (grid.length > 3) 
			assignOffLimits();
	}

	private void assignOffLimits() {
		int temp = 3;
		while (temp > 0) {
			grid[grid.length - temp][0].restrict();
			grid[grid.length - 1][temp].restrict();
			temp -= 1;
		}
	}

	public void makeRooms() {
		int roomsInBoard = getNumRooms();
		for (int i = 1; i < grid.length; i++) {
			for (int j = 1; j < grid[i].length; j++) {
				if ((i+2)%3 == 0 && (j+2)%3 == 0) {
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
					temp += 1;
				}
			}
		}
	}
	
	public void locateHallways() {
		hallways = new int[boardSize*boardSize - getNumRooms()][2];
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

	public void insertBriefcase() {
		int roomNum = Engine.roll(rooms.length);
		grid[rooms[roomNum][0]][rooms[roomNum][1]].hasBriefcase();
	}
	
	private void insertEnemies() {
		int numEnemies = 2*boardSize/3;
		while (numEnemies > 0) {
			int hallwayNum = Engine.roll(hallways.length);
			if (!grid[hallways[hallwayNum][0]][hallways[hallwayNum][1]].hasNinja()) {
				if (!grid[hallways[hallwayNum][0]][hallways[hallwayNum][1]].isOffLimits()) {
					grid[hallways[hallwayNum][0]][hallways[hallwayNum][1]].placeNinja();
					numEnemies -= 1;
				}
			}
		}
	}
	
	private void locateEnemies() {
		int numEnemies = 2*boardSize/3;
		int temp = 0;
		ninjas = new int[numEnemies][2];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j].hasNinja()) {
					ninjas[temp][0] = i;
					ninjas[temp][1] = j;
					temp += 1;
				}
			}
		}
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
	
	public void printBoard() {
		for (int i = 0 ; i < grid.length ; i++) {
			for (int j = 0 ; j < grid[i].length ; j++) {
				UI.printString("[" + grid[i][j].getSymbol() + "]");
			}
			UI.printLn();
		}
	}

	public void moveNinjas() {
		boolean validDirection = false;
		int direction = 0;
		for (int i = 0 ; i < ninjas.length ; i++) {
			do {
				direction = grid[ninjas[i][0]][ninjas[i][1]].askANinja();
				validDirection = checkValidDirection(direction, i);
			} while (validDirection == false);
			UI.printString(Arrays.toString(ninjas[i]));		//Debug
			moveNinja(i, direction);
		}
	}

	private void moveNinja(int ninja, int direction) {
		int ninjaX = ninjas[ninja][1];
		int ninjaY = ninjas[ninja][0];
		ActiveAgent tempNinja = null;
		tempNinja = grid[ninjaY][ninjaX].getNinja();
		grid[ninjaY][ninjaX].deleteNinja();
		switch (direction) {
		case 0:				//Up
			grid[ninjaY - 1][ninjaX].placeNinja(tempNinja);
			ninjas[ninja][0] -= 1;
			break;
		case 1:				//Left
			grid[ninjaY][ninjaX - 1].placeNinja(tempNinja);
			ninjas[ninja][1] -= 1;
			break;
		case 2:				//Down
			grid[ninjaY + 1][ninjaX].placeNinja(tempNinja);
			ninjas[ninja][0] += 1;
			break;
		case 3:				//Right
			grid[ninjaY][ninjaX + 1].placeNinja(tempNinja);
			ninjas[ninja][1] += 1;
			break;
		default:
			UI.callException("ToDo");
		}
	}

	private boolean checkValidDirection(int direction, int ninja) {
		int ninjaX = ninjas[ninja][1];
		int ninjaY = ninjas[ninja][0];
		switch (direction) {
		case 0:				//Up
			if (ninjaY - 1 < 0) return false;
			return grid[ninjaY - 1][ninjaX].checkIsClear();
		case 1:				//Left
			if (ninjaX - 1 < 0) return false;
			return grid[ninjaY][ninjaX - 1].checkIsClear();
		case 2:				//Down
			if (ninjaY + 1 >= grid.length) return false;
			return grid[ninjaY + 1][ninjaX].checkIsClear();
		case 3:				//Right
			if (ninjaX + 1 >= grid.length) return false;
			return grid[ninjaY][ninjaX + 1].checkIsClear();
		default:
			return false;
		}
	}
}
