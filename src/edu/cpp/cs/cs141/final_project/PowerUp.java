package edu.cpp.cs.cs141.final_project; /**
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

/**
 * This class represents the abstract concept of a power-up the spy
 * can obtain during the game, and includes the location, inherited
 * by the superclass edu.cpp.cs.cs141.final_project.Item, and a method for each type's effect.
 * 
 * @author Diana Choi
 *
 */
public abstract class PowerUp extends Item{

	/**
	 * @param row
	 * @param col
	 */
	public PowerUp(int row, int col) {
		super(row, col);
	}
	
	/**
	 * 
	 */
	public abstract void effect();

}
