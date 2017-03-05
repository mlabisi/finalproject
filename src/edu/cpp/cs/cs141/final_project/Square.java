/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodr�guez
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
 * This class represents a square on the {@link Board}.
 * It is an abstract class and can be specialized to be a
 * {@link Room} or a {@link Hallway}.
 * 
 * @author Mora Labisi
 * @author Logan Carichner
 */
public abstract class Square implements Serializable {
    private boolean lightsOn = false;
    protected String squareType;

    /**
     * @return The value of squareType
     */
    public String getType() {
        return squareType;
    }

    /**
     * @param string The type to be assigned to the square.
     */
    public abstract void setType(String string);

    /**
     * @return The value of hasPlayer
     */
    public abstract boolean hasPlayer();

    /**
     * This method is used to restrict the hallways within 3 squares of the
     * player's initial position.
     */
    public abstract void restrict();

    /**
     * @return The value of restricted
     */
    public abstract boolean isOffLimits();

    /**
     * This method returns the character to display.
     */
	public abstract char getSymbol();

    /**
     * Implements the hasBriefcase method from square.
     */
    public abstract void hasBriefcase();

    /**
     * @return The value of hasAgent
     */
    public abstract boolean hasAgent();


    /**
     * @return Returns the value of lightOn
     */
    public boolean lightsOn() {
    	return lightsOn;
    }


    /**
     * @param allLights The boolean value to be applied to lightsOn
     */
    public void switchLights(boolean allLights){
        lightsOn = allLights;
    }

    /**
     * @param item The item to be placed
     */
    public abstract void place(Item item);

    /**
     * This method sets the value of isEntrance to true.
     */
	public abstract void isEntrance();

    /**
     * @return The direction the ninja will move
     */
	public abstract int askANinja();

    /**
     * @return The value of isClear
     */
	public abstract boolean checkIsClear();

    /**
     * @return The active agent
     */
	public abstract ActiveAgent getAgent();

    /**
     * This method is called when a ninja has been shot.
     */
	public abstract void deleteAgent();

    /**
     * This method will initialize agent and place it on this square.
     */
	public abstract void placeAgent();

    /**
     * This method will place an already made agent onto this square.
     *
     * @param tempNinja The agent to be placed
     */
	public abstract void placeAgent(ActiveAgent tempNinja);

    /**
     * This method will place the player on this square.
     */
	public abstract void placeSpy();

    /**
     * @return The value of isEntrance.
     */
	public abstract boolean checkEntry();
	
}