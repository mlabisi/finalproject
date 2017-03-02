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
    private boolean checkedRadar = false;
    private boolean checkedMsg = false;
    private ActiveAgent spy;
    private ArrayList<Hallway> halls;
    private Square spySquare;
    private Square bulletSquare;
    private Square invulSquare;
    private Square radarSquare;
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

    private void insertPlayer() {
        spy = new ActiveAgent();
        spy.setRow(boardSize - 1);
        spy.setColumn(0);

        spySquare = grid[spy.getRow()][spy.getColumn()];
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
                    grid[hallways[hallwayNum][0]][hallways[hallwayNum][1]].placeAgent(new ActiveAgent());
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
        caseRoom = grid[rooms[roomNum][0]][rooms[roomNum][1]];
        caseRoom.hasBriefcase();
        caseRoom.place(new Briefcase());
    }

    private void insertItems() {
        Collections.shuffle(halls);
        ArrayList<Integer> remove = new ArrayList<>();

        for (Hallway hall : halls) {
            if (!(hall.checkIsClear()))
                remove.add(halls.indexOf(hall));
        }

        for (Integer index : remove) {
            halls.remove(index);
        }

        bulletSquare = halls.get(0);
        invulSquare = halls.get(1);
        radarSquare = halls.get(2);

        bulletSquare.place(new ExtraBullet());
        invulSquare.place(new Invulnerability());
        radarSquare.place(new Radar());

    }

    /**
     * This method calls the UI to print out the entire board in its current
     * state.
     */
    public void printBoard() {
        if (spy.checkHasRadar() && !checkedRadar) {
            caseRoom.switchLights(true);
        	checkedRadar = true;
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == spySquare){
                    UI.printString("[P]");
                    continue;
                }
                UI.printString("[" + grid[i][j].getSymbol() + "]");
            }
            UI.printLn();
        }

        if(((Hallway) spySquare).hasItem() && !checkedMsg){
            UI.printString((((Hallway) spySquare).getItem().getType().getMessage()));
            UI.printLn();
            UI.printLn();
            checkedMsg = true;
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
                    if (grid[i][j].hasAgent() && !grid[i][j].getAgent().isPlayer())
                        numEnemies++;
                }
            }
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
                    grid[i][j].switchLights(true);
                }
            }
        } else {
            for (int i = 0; i < grid.length; ++i) {
                for (int j = 0; j < grid[i].length; ++j) {
                    grid[i][j].switchLights(false);
                }
            }
        }
    }

    public void lookInDirection(int direction) {
        switch (direction) {
            case 0: // Up
                if (!(spy.getRow() - 1 < 0) && grid[spy.getRow() - 1][spy.getColumn()].getType().compareToIgnoreCase("Room") != 0) {
                    grid[spy.getRow() - 1][spy.getColumn()].switchLights(true);
                    if (!(spy.getRow() - 2 < 0) && grid[spy.getRow() - 2][spy.getColumn()].getType().compareToIgnoreCase("Room") != 0) {
                        grid[spy.getRow() - 2][spy.getColumn()].switchLights(true);
                    }
                }
                break;
            case 1: // Left
                if (!(spy.getColumn() - 1 < 0) && grid[spy.getRow()][spy.getColumn() - 1].getType().compareToIgnoreCase("Room") != 0) {
                    grid[spy.getRow()][spy.getColumn() - 1].switchLights(true);
                    if (!(spy.getColumn() - 2 < 0) && grid[spy.getRow()][spy.getColumn() - 2].getType().compareToIgnoreCase("Room") != 0) {
                        grid[spy.getRow()][spy.getColumn() - 2].switchLights(true);
                    }
                }
                break;
            case 2: // Down
                if (!(spy.getRow() + 1 >= grid.length)
                        && grid[spy.getRow() + 1][spy.getColumn()].getType().compareToIgnoreCase("Room") != 0) {
                    grid[spy.getRow() + 1][spy.getColumn()].switchLights(true);
                    if (!(spy.getRow() + 2 >= grid.length)
                            && grid[spy.getRow() + 2][spy.getColumn()].getType().compareToIgnoreCase("Room") != 0) {
                        grid[spy.getRow() + 2][spy.getColumn()].switchLights(true);
                    }
                }
                break;
            case 3: // Right
                if (!(spy.getColumn() + 1 >= grid.length)
                        && grid[spy.getRow()][spy.getColumn() + 1].getType().compareToIgnoreCase("Room") != 0) {
                    grid[spy.getRow()][spy.getColumn() + 1].switchLights(true);
                    if (!(spy.getColumn() + 2 >= grid.length)
                            && grid[spy.getRow()][spy.getColumn() + 2].getType().compareToIgnoreCase("Room") != 0) {
                        grid[spy.getRow()][spy.getColumn() + 2].switchLights(true);
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
     * @param direction The integer value representing the direction the ninja wants
     *                  to move.
     * @param ninja     The integer value representing the ninja in the ninjas array.
     * @return True if the ninja can move in the requested direction, else
     * false.
     */
    public boolean checkValidDirection(String agent, int direction, int ninja) {
        int ninjaY = playerOrNinja(agent, ninja)[0];
        int ninjaX = playerOrNinja(agent, ninja)[1];
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

    private int[] playerOrNinja(String agent, int ninja) {
        int[] indices = new int[2];

        if (agent.compareToIgnoreCase("ninjas") == 0) {
            indices[0] = ninjas[ninja][0];
            indices[1] = ninjas[ninja][1];
        } else if(agent.compareToIgnoreCase("player") == 0){
            indices[0] = spy.getRow();
            indices[1] = spy.getColumn();
        }
            UI.callException("agent");

        return indices;
    }

    /**
     * This method moves a single ninja to the location its AI has requested.
     *
     * @param ninja     The integer location of this ninja within the ninjas array
     * @param direction The integer value representing one of four directions for the
     *                  ninja to move
     */
    public void moveAgent(String agent, int direction, int ninja) {
        int ninjaY = playerOrNinja(agent, ninja)[0];
        int ninjaX = playerOrNinja(agent, ninja)[1];

        ActiveAgent tempNinja;
        tempNinja = grid[ninjaY][ninjaX].getAgent();
        // delete old ninja AFTER the switch statement
        switch (direction) {
            case 0: // Up
                if (agent.compareToIgnoreCase("player") == 0) {
                    spy.setRow(spy.getRow() - 1);
                    spySquare = grid[spy.getRow()][spy.getColumn()];
                    if(((Hallway) spySquare).hasItem()){
                        ((PowerUp) ((Hallway) spySquare).getItem()).effect(spy);
                    }
                } else {
                    grid[ninjaY - 1][ninjaX].placeAgent(tempNinja);
                    ninjas[ninja][0] -= 1;
                }
                break;
            case 1: // Left
                if (agent.compareToIgnoreCase("player") == 0) {
                    spy.setColumn(spy.getColumn() - 1);
                    spySquare = grid[spy.getRow()][spy.getColumn()];
                    if(((Hallway) spySquare).hasItem()){
                        ((PowerUp) ((Hallway) spySquare).getItem()).effect(spy);                    }
            }else {
                    ninjas[ninja][1] -= 1;
                    grid[ninjaY][ninjaX - 1].placeAgent(tempNinja);
                }
                    break;
            case 2: // Down
                if (agent.compareToIgnoreCase("player") == 0) {
                    spy.setRow(spy.getRow() + 1);
                    spySquare = grid[spy.getRow()][spy.getColumn()];
                    if(((Hallway) spySquare).hasItem()){
                        ((PowerUp) ((Hallway) spySquare).getItem()).effect(spy);                    }
                }else {
                    ninjas[ninja][0] += 1;
                    grid[ninjaY + 1][ninjaX].placeAgent(tempNinja);
                }
                    break;
            case 3: // Right
                if (agent.compareToIgnoreCase("player") == 0) {
                    spy.setColumn(spy.getColumn() + 1);
                    spySquare = grid[spy.getRow()][spy.getColumn()];
                    if(((Hallway) spySquare).hasItem()){
                        ((PowerUp) ((Hallway) spySquare).getItem()).effect(spy);                    }
                }else {
                    ninjas[ninja][1] += 1;
                    grid[ninjaY][ninjaX + 1].placeAgent(tempNinja);
                }
                    break;
            default:
                UI.callException("ToDo");
        }
        // delete the old ninja now that the movement has completed
        grid[ninjaY][ninjaX].deleteAgent();

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
                validDirection = checkValidDirection("ninjas", direction, i);
            } while (!validDirection);
            moveAgent("ninjas", direction, i);
        }
    }
    
    public void shoot(int direction) {
    	int playerX = spy.getColumn();//0
    	int playerY = spy.getRow(); //8 
        switch (direction) {
            case 0: // Shoots Up
                for (int i = playerY; i >= 0; i--) {
                	if (grid[i][playerX].hasAgent() && !grid[i][playerX].getAgent().isPlayer()){
                		grid[i][playerX].deleteAgent();
                		break;
                	}
                	else if (grid[i][playerX].getType().compareToIgnoreCase("room") == 0)
                		break;
                }
                break;
            case 1: // Shoots down
                for (int i = playerY; i < boardSize; i++) {
                	if (grid[i][playerX].hasAgent() && !grid[i][playerX].getAgent().isPlayer()){
                		grid[i][playerX].deleteAgent();
                		break;
                	}
                	else if (grid[i][playerX].getType().compareToIgnoreCase("room") == 0)
                		break;
                }
                break;
            case 2: // Shoots right
                for (int i = playerX; i < boardSize; i++) {
                	if (grid[playerY][i].hasAgent() && !grid[playerY][i].getAgent().isPlayer()){
                		grid[playerY][i].deleteAgent();
                		break;
                	}
                	else if (grid[playerY][i].getType().compareToIgnoreCase("room") == 0)
                		break;
                }
                break;
            case 3: // Shoots left
                for (int i = playerX; i >= 0; i--) {
                	if (grid[playerY][i].hasAgent() && !grid[playerY][i].getAgent().isPlayer()){
                		grid[playerY][i].deleteAgent();
                		break;
                	}
                	else if (grid[playerY][i].getType().compareToIgnoreCase("room") == 0)
                		break;
                }
                break;
        }
        locateEnemies();
    }
    //Mora's shoot method
    /*public void shoot(int direction) {
        switch (direction) {
            case 0: // Shoots Up
                for (int i = (spy.getRow() - 1); i >= 0; --i) {
                    if(grid[--i][spy.getColumn()].getType().equals("Room"))
                        break;
                    if(grid[--i][spy.getColumn()].checkIsClear())
                        continue;
                    grid[i][spy.getColumn()].deleteAgent();
                    break;
                }
                break;
            case 1: // Shoots down
                for (int i = (spy.getRow() + 1); i < boardSize; ++i) {
                    if(grid[++i][spy.getColumn()].getType().equals("Room"))
                        break;
                    if(grid[++i][spy.getColumn()].checkIsClear())
                        continue;
                    grid[i][spy.getColumn()].deleteAgent();
                    break;
                }
                break;
            case 2: // Shoots right
                for (int i = (spy.getColumn() + 1); i < boardSize; ++i) {
                    if(grid[spy.getRow()][++i].getType().equals("Room"))
                        break;
                    if(grid[spy.getRow()][++i].checkIsClear())
                        continue;
                    grid[spy.getRow()][i].deleteAgent();
                    break;
                }
                break;
            case 3: // Shoots left
                for (int i = (spy.getColumn() - 1); i >= 0; --i) {
                    if(grid[spy.getRow()][--i].getType().equals("Room"))
                        break;
                    if(grid[spy.getRow()][--i].checkIsClear())
                        continue;
                    grid[spy.getRow()][i].deleteAgent();
                    break;
                }
                break;
        }
        locateEnemies();
    }*/

    public boolean checkIfEntrance() {
        return grid[spy.getRow()][spy.getColumn()].checkEntry();
    }



    public void killNinja() {
        grid[ninjas[1][0]][ninjas[1][1]].killAgent();
        locateEnemies();
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


    public void toggleDebug() {
        debug = !debug;
    }

    public int getAmmo(){
    	return spy.getAmmo();
    }

    public Square getSpySquare(){
        return spySquare;
    }

    public Square getCaseRoom(){
        return caseRoom;
    }

}
