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
 * This class represents a square on the {@link Board}.
 * It is an abstract class and can be specialized to be a
 * {@link Room} or a {@link Hallway}.
 * 
 * @author Mora Labisi
 * @author Logan Carichner
 */
public abstract class Square {
    protected boolean lightsOn = false;
    protected String squareType;

    public abstract void setType(String string);
    
    public String getType() {
    	return squareType;
    }
    
	public abstract char getSymbol();
	
    public abstract void hasBriefcase();
    
    public boolean lightsOn() {
    	return lightsOn;
    }
    
    public void switchLights() {
    	lightsOn = !lightsOn;
    }


    /**
     * This abstract method will allow the placement of
     * an item on the current square.
     */
    public abstract void place(Item item);

	public abstract void isEntrance();
	
}