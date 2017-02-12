/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodr�guez
 *
 * Programming Assignment #4
 *
 * A simple text-based spy game in which the spy, controlled by the player, tries to find
 * the briefcase before the ninja-assassins find and kill the spy.
 *
 * Team Magic!~<3
 *   Diana Choi
 *   Logan Carichner
 *   William Hang
 *   Robert Delfin
 *   Mora Labisi
 */
package edu.cpp.cs.cs141.final_project;

/**
 * This class represents the abstract concept of an item, which encompasses the
 * different power-ups the spy can obtain, as well as the briefcase, which is the
 * objective of the game. Each item has a location on the map.
 * 
 * @author Diana Choi
 *
 */
public abstract class Item {

	/**
	 * 
	 */
	private static int locationX;
	
	/**
	 * 
	 */
	private static int locationY;
	
	/**
	 * 
	 */
	private boolean obtained;
	
	/**
	 * @param row
	 * @param col
	 */
	public Item(int row, int col) {
		obtained = false;
	}

	/**
	 * 
	 */
	public void pickedUp(){
		obtained = true;
	}
	
	/**
	 * @return
	 */
	public int getX(){
		return locationX;
	}
	
	/**
	 * @return
	 */
	public int getY(){
		return locationY;
	}
	
	/**
	 * @return
	 */
	public boolean isPickedUp(){
		return obtained;
	}
}
