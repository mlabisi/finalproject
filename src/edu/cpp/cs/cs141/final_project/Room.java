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

/**
 * This class is a {@link Square} that represents a room in the game. The room
 * can either be clear or it can contain the briefcase.
 *
 * @author Logan Carichner
 */
public class Room extends Square {

	private boolean hasBriefcase;
	private char symbol1 = 63;
	private char symbol2 = 33;

	/**
	 * This is the constructor for the room.
	 */
	public Room() {
		setType("Room");
	}

	public Room(boolean briefcase) {
		setType("Room");
		hasBriefcase = briefcase;
	}

	public void hasBriefcase() {
		hasBriefcase = true;
	}

	@Override
	public void setType(String string) {
		squareType = string;
	}

	/**
	 * @return The value of {@link #hasBriefcase}
	 */
	public boolean checkHasBriefcase() {
		return hasBriefcase;
	}

	@Override
	public void place(Item item) {
	}

	@Override
	public void isEntrance() {
	}
	
	@Override
	public char getSymbol() {
		if (lightsOn() && hasBriefcase)
			return symbol2;
		else //if (lightsOn())
			return symbol1;
//		else
//			return 35;
	}

}
