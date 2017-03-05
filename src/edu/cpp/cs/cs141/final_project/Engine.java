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

/**
 * @author Robert Delfin
 * @author Logan Carichner
 *
 */
public class Engine implements Serializable {
    private Board board;
    private boolean debug;
    private int turns;
    private int lives;
    private int boardSize;
    private boolean win = false;

    /**
     * This constructor for the engine is used for testing purposes.
     * It can create a scalable board and gives the choice of starting  
     * the game with debug mode on or off.
     */
    public Engine() {
        boardSize = UI.getBoardSize();
        debug = UI.debugStart();
        board = new Board(boardSize, debug);
        turns = 0;
    }
    

    /**
     * This constructor for the engine is used to create the board that
     * is ready for actual gameplay. The board is a predefined size and  
     * debug mode is off.
     */
    public Engine(int i) {
    	boardSize = i;
    	debug = false;
    	board = new Board(boardSize, debug);
    }

    /**
     * This method will call the printBoard method on the game board.
     */
    public void printBoard() {
        board.printBoard();
    }

    /**
     * This engine simulates the rolling of a die for randomization.
     *
     * @param num The number of sides the die has
     * @return The number that was rolled
     */
    public static int roll(int num) {
        int generated = (int) Math.floor(Math.random() * (num));
        return generated;
    }

    /**
     * This method will call the moveNinjas method on the board 
     * and increment the turns counter. 
     */
    public void enemyTurn() {
        board.moveNinjas();
        turns++;
    }

    /**
     * This method will provide a message that states the status
     * of debug mode.
     *
     * @return The appropriate message
     */
    public String getDebug() {
        if (debug)
            return "ON";
        return "OFF";
    }


    /**
     * This method will toggle debug mode for all components
     * of the game.
     */
    public void toggleDebug() {
        debug = !debug;
        board.toggleDebug();
        board.debugRooms();
    }


    /**
     * This method will get the number of rooms on the board.
     *
     * @return The number of rooms
     */
    public int getNumRooms() {
        return board.getNumRooms();
    }


    /**
     * This method will check to see if the player has won
     * the game.
     *
     * @return The value of boolean flag win
     */
    public boolean getState() {
        return win;
    }

    /**
     * This method is used to quit the game.
     */
    public void quit() {
        System.exit(0);
    }

    /**
     * This method is used to make sure the agent's move is valid
     *
     * @param direction The int representatoin of the direction
     * @return Boolean value
     */
    public boolean checkPlayerMove(int direction) {
        return board.checkValidDirection("player", direction, 0);
    }

    /**
     * This method is used to move agents on the board.
     * 
     * @param direction The int representation of the direction
     */
    public void movePlayer(int direction) {
        board.moveAgent("player", direction, 0);
    }

    /**
     * This method will call the checkIfEntrance method on the game board.
     *
     * @return Boolean value
     */
    public boolean checkEntrance() {
        return board.checkIfEntrance();
    }

    /**
     * This method will call the lookInDir method on the game board.
     *
     * @param direction The int representation of the direction
     */
    public void lookInDir(int direction) {
        board.lookInDirection(direction);
    }


    /**
     * This method will check to see if the player has ammo.
     *
     * @return Boolean value
     */
    public boolean checkGun(){
        return (board.getAmmo() == 1) ? true : false;
    }


    /**
     * This method will call the shoot method on the game board.
     *
     * @param direction The int representation of the direction
     */
    public void shootGun(int direction) {
        board.shoot(direction);
    }
    

    /**
     * This method will check how much ammo the player has.
     *
     * @return The amount of ammo
     */
    public int getPlayerAmmo(){
    	return board.getAmmo();
    }

    /**
     * This method will check how many lives the player has.
     *
     * @return The number of lives
     */
    public int getLives(){
//    	board.updateLives();
    	lives = board.getPlayerLives();
    	return lives;
    }

    /**
     * This method will call the checkCaseRoom on the game board. 
     *
     * @return Boolean value
     */
    public boolean checkCaseRoom(){
    	return board.checkCaseRoom();
    }

    /**
     * This method is called when the user has found the briefcase.
     */
    public void winGame(){
    	win = true;
    }
}
