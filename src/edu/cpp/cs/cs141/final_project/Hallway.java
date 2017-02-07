/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodríguez
 *
 * Final Project: Spy Game
 *
 * Create a small, yet interesting, text-based game involving a spy
 * an ninjas.
 *
 * Diana Choi, Will Hang, Logan, Robert Delfin, Mora Labisi
 */
package edu.cpp.cs.cs141.final_project;

/**
 * This class is a {@link Square} that represents a
 * hallway in the game. The hallway can either be
 * clear or there can be ninjas present.
 *
 */
public class Hallway extends Square {
    /**
     * This {@code boolean} flag represents whether
     * or not {@code this} {@link Hallway} is clear.
     */
    private boolean isClear;

    /**
     * This is the constructor for the hallway class.
     */
    public Hallway(){

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

    }

    /**
     * This method will allow the placement of
     *
     * @param powerUp The {@link PowerUp} object
     */
    @Override
    public void place(PowerUp powerUp){

    }

    /**
     * This method will reveal whether or not {@code this} {@link Hallway}
     * is clear after the {@link ActiveAgent} looks.
     *
     * @return The appropriate message
     */
    @Override
    public String reveal(){
        return "";
    }
}
