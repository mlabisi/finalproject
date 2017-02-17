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
 */
public abstract class Square {

    /**
     * This {@code boolean} flag represents whether
     * or not {@code this} {@link Square} is clear.
     */
    private boolean isClear;

    /**
     * This {@code boolean} flag will represent
     * whether or not the lights are on in this
     * square.
     */
    private boolean lightsOn;

    private String letter;

    /**
     * This {@code boolean} flag will represent
     * whether or not the user is on {@code this}
     * {@link Square}.
     */
    private boolean hasUser;

    /**
     * This represents the type of square.
     */
    final private SquareType TYPE;

    /**
     * This is the constructor for the square.
     */
    public Square(SquareType type){
        this.TYPE = type;
        letter = getType().toString();
        isClear = true;
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
    public void setIsClear(boolean value){
        isClear = value;
    }

    /**
     * @return The value of {@link #lightsOn}
     */
    public boolean checkLightsOn(){
        return lightsOn;
    }

    /**
     * @return The value of {@link #hasUser}
     */
    public boolean checkHasUser(){
        return hasUser;
    }

    /**
     * This method will represent turning the
     * lights on or off.
     *
     * @param value The value to be given to {@link #lightsOn}
     */
    public void setLightsOn(boolean value){

    }

    /**
     * @param value The value to be given to {@link #hasUser}
     */
    public void setHasPlayer(boolean value){
        hasUser = value;
    }

    public SquareType getType(){
        return TYPE;
    }

    /**
     * This abstract method will allow the placement of
     * an item on the current square.
     */
    public void place(Item item){
        setIsClear(false);
        letter = item.getType().toString();
    }

    public void place(ActiveAgent agent){
        setIsClear(false);
        if(agent.getType() == AgentType.PLAYER)
            setHasPlayer(true);
        letter = agent.getType().toString();
    }

    /**
     * This abstract method will make sure to display
     * the state of {@code this} {@link Square}.
     *
     * @return The approprite icon/letter
     */
    public String reveal(){
        if(hasUser)
            return AgentType.PLAYER.toString();
        return letter;
    }
}