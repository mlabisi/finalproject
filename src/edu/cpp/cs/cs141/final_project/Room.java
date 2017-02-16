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
public class Room extends Square {/**
     * This {@code boolean} flag will represent
     * whether or not the room contains the
     * briefcase.
     */


    /**
     * This is the constructor for the room.
     */
    public Room(){
        super(SquareType.ROOM);
    }

    @Override
    public String toString(){
        return super.getType().toString();
    }

}