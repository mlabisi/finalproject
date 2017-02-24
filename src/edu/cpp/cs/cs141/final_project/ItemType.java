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
 * This enum type will represent the specific type of items that
 * exist in the spy game.
 *
 * @author Mora Labisi
 */
public enum ItemType {
    RADAR('R'),
    INVULNERABILITY('I'),
    EXTRA_BULLET('B'),
    BRIEFCASE('!');

    final private char ICON;

    /**
     * @param icon The icon to be assigned
     */
    ItemType(char icon){
        this.ICON = icon;
    }

    /**
     * @return The {@code char} representation of the item.
     */
    public char toChar(){
        return ICON;
    }
}