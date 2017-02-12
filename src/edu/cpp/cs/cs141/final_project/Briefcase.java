package edu.cpp.cs.cs141.final_project;
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

/**
 * This class represents the edu.cpp.cs.cs141.final_project.Briefcase edu.cpp.cs.cs141.final_project.Item, which simply inherits its location
 * from its superclass edu.cpp.cs.cs141.final_project.Item.
 * 
 * @author Diana Choi
 *
 */
public class Briefcase extends Item{

	/**
	 * @param row
	 * @param col
	 */
	public Briefcase(int row, int col) {
		super(row, col);
	}

}
