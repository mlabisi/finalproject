/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodriguez
 * <p>
 * Final Project: Spy Game
 * <p>
 * Create a small, yet interesting, text-based game involving a spy
 * and ninjas.
 * <p>
 * Team Magic!~<3
 * Diana Choi, William Hang, Logan Carichner, Robert Delfin, Mora Labisi
 */
package edu.cpp.cs.cs141.final_project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

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
	private boolean init;
	private int[] player;
	private boolean checkedRadar = false;
	private boolean checkedMsg = false;
	private ArrayList<Hallway> halls;
	private Square caseRoom;

	final private int boardSize;

	/**
	 * This is the default constructor for the board.
	 */
	public Board(boolean debug) {
		boardSize = 9;
		this.debug = debug;
		init = true;
		halls = new ArrayList<>();
		makeBoard();
		fillBoard();
		makeRooms();
		locateRooms();
		locateHallways();
		insertPlayer();
		insertEnemies();
		insertBriefcase();
		insertItems();
		locateEnemies();
		debugRooms();
		init = false;
	}

	public Board(int size, boolean debug) {
		boardSize = size;
		this.debug = debug;
		init = true;
		halls = new ArrayList<>();
		makeBoard();
		fillBoard();
		makeRooms();
		locateRooms();
		locateHallways();
		insertPlayer();
		insertEnemies();
		insertBriefcase();
		insertItems();
		locateEnemies();
		debugRooms();
		init = false;
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
				if ((i == (boardSize - 1)) && j == 0) {
					continue;
				}
				halls.add((Hallway) grid[i][j]);
			}
		}
		if (grid.length > 3)
			assignOffLimits();
	}

	/**
	 * This method replaces the hallway object with a room object inside each
	 * 3x3 square section of the grid
	 */
	public void makeRooms() {
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

	private void insertPlayer() {
		player = new int[2];
		player[0] = boardSize - 1;
		player[1] = 0;
		grid[player[0]][player[1]].placeSpy();
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
	 * This method places the briefcase object within one of the rooms at
	 * random.
	 */
	public void insertBriefcase() {
		int roomNum = Engine.roll(rooms.length);
		grid[rooms[roomNum][0]][rooms[roomNum][1]].hasBriefcase();
	}

	private void insertItems() {
		Collections.shuffle(halls);
		ArrayList<Integer> remove = new ArrayList<>();

		for (Hallway hall : halls)
			if (!(hall.checkIsClear()))
				remove.add(halls.indexOf(hall));

		for (Integer index : remove)
			halls.remove(index);

		halls.get(0).place(new ExtraBullet());
		halls.get(1).place(new Invulnerability());
		halls.get(2).place(new Radar());
	}

	/**
	 * This method calls the UI to print out the entire board in its current
	 * state.
	 */
	public void printBoard() {

		checkRadarEffect();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				UI.printString("[" + grid[i][j].getSymbol() + "]");
			}
			UI.printLn();
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
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[i].length; j++) {
					if (grid[i][j].hasAgent() && !grid[i][j].hasPlayer())
						numEnemies++;
				}
			}
		int temp = 0;
		ninjas = new int[numEnemies][2];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j].hasAgent() && !grid[i][j].hasPlayer() && temp < ninjas.length) {
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
		if (debug)
			for (int i = 0; i < grid.length; i++)
				for (int j = 0; j < grid[i].length; j++)
					grid[i][j].switchLights(true);
		else
			for (int i = 0; i < grid.length; ++i)
				for (int j = 0; j < grid[i].length; ++j)
					grid[i][j].switchLights(false);
	}

	public void lookInDirection(int direction) {
		int Y = player[0];
		int X = player[1];
		switch (direction) {
		case 0: // Up
			if (!(Y - 1 < 0) && grid[Y - 1][X].getType().compareToIgnoreCase("Room") != 0) {
				grid[Y - 1][X].switchLights(true);
				if (!(Y - 2 < 0) && grid[Y - 2][X].getType().compareToIgnoreCase("Room") != 0) {
					grid[Y - 2][X].switchLights(true);
				}
			}
			break;
		case 1: // Left
			if (!(X - 1 < 0) && grid[Y][X - 1].getType().compareToIgnoreCase("Room") != 0) {
				grid[Y][X - 1].switchLights(true);
				if (!(Y - 2 < 0) && grid[Y][X - 2].getType().compareToIgnoreCase("Room") != 0) {
					grid[Y][X - 2].switchLights(true);
				}
			}
			break;
		case 2: // Down
			if (!(Y + 1 >= grid.length) && grid[Y + 1][X].getType().compareToIgnoreCase("Room") != 0) {
				grid[Y + 1][X].switchLights(true);
				if (!(Y + 2 >= grid.length) && grid[Y + 2][X].getType().compareToIgnoreCase("Room") != 0) {
					grid[Y + 2][X].switchLights(true);
				}
			}
			break;
		case 3: // Right
			if (!(X + 1 >= grid.length) && grid[Y][X + 1].getType().compareToIgnoreCase("Room") != 0) {
				grid[Y][X + 1].switchLights(true);
				if (!(X + 2 >= grid.length) && grid[Y][X + 2].getType().compareToIgnoreCase("Room") != 0) {
					grid[Y][X + 2].switchLights(true);
				}
			}
			break;
		default:
			break;
		}
		printBoard();
		debugRooms();
	}

	/**
	 * This method checks to see if the agent can move in the direction it has
	 * requested.
	 *
	 * @param direction
	 *            The integer value representing the direction the ninja wants
	 *            to move.
	 * @param ninja
	 *            The integer value representing the ninja in the ninjas array.
	 * @return True if the ninja can move in the requested direction, else
	 *         false.
	 */
	public boolean checkValidDirection(String agent, int direction, int ninja) {
		int agentY = (agent.compareToIgnoreCase("player") == 0) ? player[0] : ninjas[ninja][0];
		int agentX = (agent.compareToIgnoreCase("player") == 0) ? player[1] : ninjas[ninja][1];
		
		switch (direction) {
		case 0: // Up
			if (agentY - 1 < 0)
				return false;
			return grid[agentY - 1][agentX].checkIsClear();
		case 1: // Left
			if (agentX - 1 < 0)
				return false;
			return grid[agentY][agentX - 1].checkIsClear();
		case 2: // Down
			if (agentY + 1 >= grid.length)
				return false;
			return grid[agentY + 1][agentX].checkIsClear();
		case 3: // Right
			if (agentX + 1 >= grid.length)
				return false;
			return grid[agentY][agentX + 1].checkIsClear();
		default:
			return false;
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
	public void moveAgent(String agent, int direction, int ninja) {
		int agentY = (agent.compareToIgnoreCase("player") == 0) ? player[0] : ninjas[ninja][0];
		int agentX = (agent.compareToIgnoreCase("player") == 0) ? player[1] : ninjas[ninja][1];

		ActiveAgent tempAgent = grid[agentY][agentX].getAgent();
		// delete old agent AFTER the switch statement
		switch (direction) {
		case 0: // Up
			if (agent.compareToIgnoreCase("player") == 0) {
				player[0]--;
			} else
				ninjas[ninja][0]--;
			grid[agentY - 1][agentX].placeAgent(tempAgent);
			break;
		case 1: // Left
			if (agent.compareToIgnoreCase("player") == 0) {
				player[1]--;
			} else
				ninjas[ninja][1]--;
			grid[agentY][agentX - 1].placeAgent(tempAgent);
			break;
		case 2: // Down
			if (agent.compareToIgnoreCase("player") == 0) {
				player[0]++;
			} else
				ninjas[ninja][0]++;
			grid[agentY + 1][agentX].placeAgent(tempAgent);
			break;
		case 3: // Right
			if (agent.compareToIgnoreCase("player") == 0) {
				player[1]++;
			} else
				ninjas[ninja][1]++;
			grid[agentY][agentX + 1].placeAgent(tempAgent);
			break;
		default:
			UI.callException("ToDo");
		}
		// delete the old agent now that the movement has completed
		grid[agentY][agentX].deleteAgent();
		getPowerUp();
	}

	public void locatePlayer() {
		int i = 0;
		int j = 0;
		for ( ; i < grid.length; i++) {
			for ( ; j < grid.length; j++) {
				if (grid[i][j].hasPlayer()) {
					player[0] = i;
					player[1] = j;
				}
			}
		}
	}

	public void getPowerUp() {
		int Y = player[0];
		int X = player[1];
		Hallway hall = (Hallway)(grid[Y][X]);
		if (hall.hasItem())
			hall.useItem(hall.getItem());
	}
	
	/**
	 * This method moves each ninja's location.
	 */
	public void moveNinjas() {
		int direction = 0;
		boolean validDirection = false;
		for (int i = 0; i < ninjas.length; i++) {
			int tries = 0;
			boolean stabDue = false;
			do {
				direction = grid[ninjas[i][0]][ninjas[i][1]].askANinja();
				validDirection = checkValidDirection("ninjas", direction, i);
				stabDue = tryStab(i);			
				tries++;
			} while (!validDirection && tries < 10 && !stabDue);
				
			if (!stabDue && tries != 10)
				moveAgent("ninjas", direction, i);
			else if (stabDue)
				stab();
			locateEnemies();
		}
	}

	public boolean tryStab(int ninja) {
		boolean stabDue = false;
		int Y = ninjas[ninja][0];
		int X = ninjas[ninja][1];
		for (int i = 0; i < 4; i++) {
			switch (i) {
			case 0:		//Up
				if (Y - 1 < 0)
					break;
				if (grid[Y - 1][X].hasPlayer())
					stabDue = true;
				break;
			case 1:		//Left
				if (X - 1 < 0)
					break;
				if (grid[Y][X - 1].hasPlayer())
					stabDue = true;
				break;
			case 2:		//Down
				if (Y + 1 >= grid.length)
					break;
				if (grid[Y + 1][X].hasPlayer())
					stabDue = true;
				break;
			case 3:	 	//Right
				if (X + 1 >= grid.length)
					break;
				if (grid[Y][X + 1].hasPlayer())
					stabDue = true;
				break;
			default:
				break;
			}
		}
		return stabDue;
	}
	
	/**
	 * 
	 */
	public void stab() {
		UI.stab();

		grid[player[0]][player[1]].getAgent().takeDamage(1);
		resetPlayerPos();
	}

	public void resetPlayerPos() {
		ActiveAgent temp = grid[player[0]][player[1]].getAgent();
		grid[player[0]][player[1]].deleteAgent();
		player[0] = boardSize - 1;
		player[1] = 0;
		grid[player[0]][player[1]].placeAgent(temp);
	}

	public void updateLives() {
		int HP = ((Hallway) grid[player[0]][player[1]]).getAgentHealth();
	}
	

	public int getPlayerLives() {
		locatePlayer();
		return ((Hallway) grid[player[0]][player[1]]).getAgentHealth();
	}


	public void shoot(int direction) {
		int playerY = player[0]; 	// 8
		int playerX = player[1];	// 0
		switch (direction) {
		case 0: // Shoots Up
			for (int i = playerY; i >= 0; i--) {
				if (grid[i][playerX].hasAgent() && !grid[i][playerX].getAgent().isPlayer()) {
					grid[i][playerX].deleteAgent();
					break;
				} else if (grid[i][playerX].getType().compareToIgnoreCase("room") == 0)
					break;
			}
			break;
		case 1: // Shoots down
			for (int i = playerY; i < boardSize; i++) {
				if (grid[i][playerX].hasAgent() && !grid[i][playerX].getAgent().isPlayer()) {
					grid[i][playerX].deleteAgent();
					break;
				} else if (grid[i][playerX].getType().compareToIgnoreCase("room") == 0)
					break;
			}
			break;
		case 2: // Shoots right
			for (int i = playerX; i < boardSize; i++) {
				if (grid[playerY][i].hasAgent() && !grid[playerY][i].getAgent().isPlayer()) {
					grid[playerY][i].deleteAgent();
					break;
				} else if (grid[playerY][i].getType().compareToIgnoreCase("room") == 0)
					break;
			}
			break;
		case 3: // Shoots left
			for (int i = playerX; i >= 0; i--) {
				if (grid[playerY][i].hasAgent() && !grid[playerY][i].getAgent().isPlayer()) {
					grid[playerY][i].deleteAgent();
					break;
				} else if (grid[playerY][i].getType().compareToIgnoreCase("room") == 0)
					break;
			}
			break;
		}
		grid[playerY][playerX].getAgent().shoot();
		locateEnemies();
	}

	public boolean checkIfEntrance() {
		return grid[player[0]][player[1]].checkEntry();
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
	 * 
	 */
	public void toggleDebug() {
		debug = !debug;
	}


	public int getAmmo() {
		return grid[player[0]][player[1]].getAgent().getAmmo();
	}


	public boolean checkCaseRoom() {
		return ((Room) (grid[player[0] + 1][player[1]])).checkHasBriefcase();
	}


	public void checkRadarEffect() {
		if (grid[player[0]][player[1]].getAgent().checkHasRadar()) {
			for (int i = 0; i < rooms.length; ++i) {
				grid[rooms[i][0]][rooms[i][1]].switchLights(true);
			}
		}
	}
}