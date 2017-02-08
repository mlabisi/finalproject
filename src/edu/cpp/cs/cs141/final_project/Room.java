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
 * room in the game. The room can either be
 * clear or it can contain the briefcase.
 *
 * @author Mora Labisi
 */
public class Room extends Square {
    /**
     * This {@code boolean} flag will represent
     * whether or not the room contains the
     * briefcase.
     */
    private boolean hasBriefcase;


    /**
     * This is the constructor for the room.
     */
    public Room(){
        super(squareType.ROOM);
    }

    /**
     * @return The value of {@link #hasBriefcase}
     */
    public boolean checkHasBriefcase(){
        return hasBriefcase;
    }

    /**
     * This method will be used to "place" the briefcase in
     * {@code this} {@link Room}.
     *
     * @param value
     */
    public void setHasBriefcase(boolean value){

    }

    /**
     * This method will allow the placement of the
     * briefcase in the room.
     *
     * @param item The {@link Briefcase} object
     */
    @Override
    public void place(Item item){
        setHasBriefcase(true);
    }

    /**
     * This method will reveal whether or not {@code this} {@link Room}
     * contains the briefcase.
     *
     * @return The appropriate message
     */
    @Override
    public String reveal(){
        return "R";
    }

}
