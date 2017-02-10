/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodríguez
 *
 * Programming Assignment #N
 *
 * <description-of-assignment>
 *
 * Team #N / (or name if individual)
 *   <team-member-names-if-team-assignment>
 */
package edu.cpp.cs.cs141.final_project.Diana;

import edu.cpp.cs.cs141.final_project.*;

/**
 * This class represents the Board which holds a 9x9 grid of {@link Square},
 * and its subclasses {@link Hallway} and {@link Room}.
 * 
 * @author Diana Choi
 *
 */
public class Board {

	/**
	 * 
	 */
	private Square[][] grid;
	
	/**
	 * 
	 */
	public static final int BOARD_SIZE = 9;
	
	
	/**
	 * 
	 */
	public Board() {
		grid = new Square[BOARD_SIZE][BOARD_SIZE];
	}
	
	public void setBoard(){
		for(int i = 0; i < BOARD_SIZE; ++i){
			for( int j = 0; j < BOARD_SIZE; ++j){
				if(i%3 == 1 && j%3 == 1){
					grid[i][j] = new Room();
				}else{
					grid[i][j] = new Hallway();
				}
			}
		}
	}
	

}
