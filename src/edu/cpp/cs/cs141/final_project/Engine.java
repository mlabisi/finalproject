/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodríguez
 *
 * Programming Assignment Final Project
 *
 * To make a game that has a spy that has
 * to find a briefcase in a building full
 * of ninjas.
 *
 * Team Magic!~<3
 *   Robert Delfin
 *   William Hang
 *   Diana Choi
 *   Logan Carichner
 *   Mora Labisi
 */

package edu.cpp.cs.cs141.final_project;

/**
 * @author Robert Delfin
 * @author Logan Carichner
 *
 */
public class Engine {
	private Board board;
	private boolean debug;
	private int turns;
	private int lives;
	private int enemies;
	private int boardSize;
	
	public Engine() {
		boardSize = UI.getBoardSize();
		debug = UI.debugStart();
		board = new Board(boardSize, debug);
		turns = 0;
		
	}
	
	public void printBoard() {
		board.printBoard();
	}
	
	public void printBoardDebug() {
		board.printBoardDebugged();
	}
	
	public static int roll(int num) {
		int generated = (int) Math.floor(Math.random() * num);
		return generated;
	}

	public void enemyTurn() {
		// TODO Auto-generated method stub
		
	}
	

}
