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
     * This {@code boolean} flag will represent
     * whether or not the lights are on in this
     * square.
     */
    private boolean lightsOn;


    /**
     * This {@code boolean} flag will represent
     * whether or not the user is on {@code this}
     * {@link Square}.
     */
    private boolean hasUser;

    /**
     * This represents the type of square.
     */
    private squareType type;

    public enum squareType{HALLWAY, ROOM}

    /**
     * This is the constructor for the square.
     */
    public Square(squareType type){
        this.type = type;
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
    public void setHasUser(boolean value){

    }

    public squareType getType(){
        return type;
    }

    /**
     * This abstract method will allow the placement of
     * an item on the current square.
     */
    public abstract void place(Item item);

    /**
     * This abstract method will make sure to tell the user wht
     * the state of the square is.
     *
     * @return The approprite message
     */
    public abstract String reveal();
}
