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
package edu.cpp.cs.cs141.final_project.Mora;

import edu.cpp.cs.cs141.final_project.Item;
import edu.cpp.cs.cs141.final_project.PowerUp;

/**
 * This class is a {@link Square} that represents a
 * hallway in the game. The hallway can either be
 * clear or there can be ninjas present.
 *
 * @author Mora Labisi
 */
public class Hallway extends Square {
    /**
     * This {@code boolean} flag represents whether
     * or not {@code this} {@link Hallway} is clear.
     */
    private boolean isClear;

    /**
     * This {@code boolean} flag will determine
     * whether or not this hallway is the entrance
     * to a {@link Room}. Entrances have the coordinates
     * grid[i-1][j] if the room's coordinates are
     * grid[i][j].
     */
    private boolean isEntrance;

    /**
     * This is the constructor for the hallway class.
     */
    public Hallway(){
        super(SquareType.HALLWAY);
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
    public void setIsEntrance(boolean value){

    }

    /**
     * This method will allow the placement a
     * {@link PowerUp}.
     *
     * @param item The {@link PowerUp} object
     */
    @Override
    public void place(Item item){
        setIsClear(false);
        if(item.is)
    }


    /**
     * This method will reveal whether or not {@code this} {@link Hallway}
     * is clear after the {@link ActiveAgent} looks.
     *
     * @return The appropriate message
     */
    @Override
    public String reveal(){
        if(isClear)
            return "";
        else

    }

    /**
     * This will return the letter representation of the user,
     * the enemy, or an item.
     *
     * @return The appropriate letter
     */
    @Override
    public String toString(){
        return " ";
    }
}
