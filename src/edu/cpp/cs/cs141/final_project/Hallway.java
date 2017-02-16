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
 * This class is a {@link Square} that represents a
 * hallway in the game. The hallway can either be
 * clear or there can be ninjas present.
 *
 * @author Mora Labisi
 * @author Logan Carichner
 */
public class Hallway extends Square {
    private boolean isClear;
    private boolean isEntrance;
    private boolean hasNinja;
    private ActiveAgent ninjaAssassin;
    private Item item;
    
    /**
     * This is the constructor for the hallway class.
     */
    public Hallway(){
    	isClear = true;
    	isEntrance = false;
    	setType("Hallway");
    }

    public void placeNinja() {
    	ninjaAssassin = new ActiveAgent();
    }
    
    public boolean hasNinja() {
    	return hasNinja;
    }
    
    @Override
    public void setType(String string) {
    	squareType = string;
    	
    }
    
    @Override
    public void isEntrance() {
    	isEntrance = true;
    }
    
    public boolean checkEntry() {
    	return isEntrance;
    }
    
    @Override
    public void hasBriefcase(){
    }
    /**
     * @return The value of {@link #isClear}
     */
    public boolean checkIsClear(){
        return isClear;
    }

    /**
     * This method will change {@link #isClear} depending
     * on whether or not there is an enemy present.
     *
     * @param value The value to be assigned to {@link #isClear}
     */
    public void clear() {
    	isClear = !isClear;
    }

    /**
     * @return The value of {@link #isEntrance}
     */
    public boolean checkIsEntrance(){
        return isEntrance;
    }

    /**
     * This method will be used to tell whether or not
     * a hallway is an entrance.
     *
     * @param value The value to be given to {@link #isEntrance}
     */
    public void setEntrance() {
    	isEntrance = true;
    }

	@Override
	public void place(Item item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public char getSymbol() {
		if (lightsOn() && hasNinja) {
			return 78;
		}else if (lightsOn()) {
			return 32;
		}
		return 35;
	}
}
