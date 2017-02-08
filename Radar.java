/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodríguez
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
 * This class represents the power-up that grants the spy the ability
 * to see the room the briefcase is located in. It inherits the location
 * of the power-up from its superclass Item.
 * 
 * @author Diana Choi
 *
 */
public class Radar extends PowerUp{

	/**
	 * @param row
	 * @param col
	 */
	public Radar(int row, int col) {
		super(row, col);
	}

	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.PowerUp#effect()
	 */
	@Override
	public void effect() {
		//sets briefcase room to visible
	}

}
