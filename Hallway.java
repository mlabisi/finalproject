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

import java.io.Serializable;

/**
 * This class is a {@link Square} that represents a
 * hallway in the game. The hallway can either be
 * clear or there can be ninjas present.
 *
 * @author Mora Labisi
 * @author Logan Carichner
 */
public class Hallway extends Square implements Serializable {
    private boolean isClear;
    private boolean isEntrance;
    private boolean hasAgent;
    private boolean hasPlayer;
	private boolean hasItem;
    private boolean restricted = false;
    private ActiveAgent agent;
    private Item item;
    private char symbol;

    /**
     * This is the constructor for the hallway class.
     */
    public Hallway(){
    	isClear = true;
    	isEntrance = false;
    	setType("Hallway");
    }
    
    public void placeSpy() {
    	agent = new ActiveAgent("player");
    	isClear = false;
    	hasAgent = true;
    	hasPlayer = true;
    }

    public void killAgent() {
    	agent.takeDamage(10);
    	agent = null;
    	hasAgent = false;
    }

	public boolean hasItem() {
		return hasItem;
	}

	public void toggleHasItem() {
		hasItem = !hasItem;
	}

    public void placeAgent() {
    	agent = new ActiveAgent();
    	isClear = false;
    	hasAgent = true;
    }
    
    public void placeAgent(ActiveAgent ninja) {
    	agent = ninja;
    	if (agent.isPlayer()){
    		hasPlayer = true;
    		useItem();
    	}
    	isClear = false;
    	hasAgent = true;
    }
    
    public boolean hasAgent() {
    	return hasAgent;
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
 * This method changes the hallway's #isClear boolean to the opposite of its current value
 */
    public void clear() {
    	isClear = !isClear;
    }

    /**
     * This method checks whether the hallway is considered an entrance
     * @return the value of #isEntrance
     */
    public boolean checkIsEntrance(){
        return isEntrance;
    }

    /**
     * This method sets the hallway as an entrance.
     */
    public void setEntrance() {
    	isEntrance = true;
    }

	@Override
	public void place(Item item) {
    	this.item = item;
		symbol = item.getType().toChar();
		hasItem = true;
		isClear = false;
	}

	private void useItem(){
    	if(hasItem())
			((PowerUp)item).effect(agent);

	}
	@Override
	/**
	 * This method overrides the abstract square's method, and returns the character to display
	 * 
	 */
	public char getSymbol() {
		if (hasPlayer) {
			return 80;
		}else if (hasAgent && lightsOn()) {
			return 78;
		}else if (hasItem && lightsOn()) {
			return symbol;
		}else if (lightsOn()) {
			return 32;
		}
		return 42;
	}

	public void restrict() {
		restricted = true;		
	}
	
	public boolean isOffLimits() {
		return restricted;
	}
	
	public ActiveAgent getAgent() {
		return agent;
	}
	
	public void deleteAgent() {
		agent = null;
		isClear = true;
		hasAgent = false;
		hasPlayer = false;
	}
	
	public int askANinja() {
		return agent.agentMove();
	}
}
