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
    protected boolean lightsOn = false;
    protected String squareType;

    public abstract void setType(String string);
    
    public String getType() {
    	return squareType;
    }
    
    public abstract void restrict();
    
    public abstract boolean isOffLimits();
    
	public abstract char getSymbol();
	
    public abstract void hasBriefcase();
    
    public abstract boolean hasAgent();
    
    public boolean lightsOn() {
    	return lightsOn;
    }
    
    public void switchLights() {
    	lightsOn = !lightsOn;
    }

    public abstract void place(Item item);

	public abstract void isEntrance();

	public abstract int askANinja();

	public abstract boolean checkIsClear();

	public abstract ActiveAgent getAgent();

	public abstract void deleteAgent();

	public abstract void placeAgent();
	
	public abstract void placeAgent(ActiveAgent tempNinja);

	public abstract void killAgent();

	public abstract void placeSpy();

	public abstract boolean checkEntry();
	
}