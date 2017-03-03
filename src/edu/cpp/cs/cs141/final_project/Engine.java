/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodriguez
 * <p>
 * Programming Assignment Final Project
 * <p>
 * To make a game that has a spy that has
 * to find a briefcase in a building full
 * of ninjas.
 * <p>
 * Team Magic!~<3
 * Robert Delfin
 * William Hang
 * Diana Choi
 * Logan Carichner
 * Mora Labisi
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

    public Engine() {
        boardSize = UI.getBoardSize();
        debug = UI.debugStart();
        board = new Board(boardSize, debug);
        turns = 0;
    }
    
    public Engine(int i) {
    	boardSize = i;
    	debug = false;
    	board = new Board(boardSize, debug);
    }

    public void printBoard() {
        board.printBoard();
    }

    public static int roll(int num) {
        int generated = (int) Math.floor(Math.random() * (num));
        return generated;
    }

    public void enemyTurn() {
        board.moveNinjas();
        turns++;
    }

    public String getDebug() {
        if (debug)
            return "ON";
        return "OFF";
    }

    public void toggleDebug() {
        debug = !debug;
        board.toggleDebug();
        board.debugRooms();
    }

    public int getNumRooms() {
        return board.getNumRooms();
    }

    public boolean getState() {
        return win;
    }

    public void quit() {
        System.exit(0);
    }

    public boolean checkPlayerMove(int direction) {
        return board.checkValidDirection("player", direction, 0);
    }

    public void movePlayer(int direction) {
        board.moveAgent("player", direction, 0);
    }

    public boolean checkEntrance() {
        return board.checkIfEntrance();
    }

    public void lookInDir(int direction) {
        board.lookInDirection(direction);
    }

    public boolean checkGun(){
        return (board.getAmmo() == 1) ? true : false;
    }

    public void shootGun(int direction) {
        board.shoot(direction);
    }
    
    public int getPlayerAmmo(){
    	return board.getAmmo();
    }
    
    public int getLives(){
//    	board.updateLives();
    	lives = board.getPlayerLives();
    	return lives;
    }
    
    public boolean checkCaseRoom(){
    	return board.checkCaseRoom();
    }
    
    public void winGame(){
    	win = true;
    }
}