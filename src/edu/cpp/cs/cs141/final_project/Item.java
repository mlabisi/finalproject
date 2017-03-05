/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodriguez
 *
 * Programming Assignment #4
 *
 * A simple text-based spy game in which the spy, controlled by the player, tries to find
 * the briefcase before the ninja-assassins find and kill the spy.
 *
 * Team Magic!~<3
 *   Diana Choi
 *   Logan Carichner
 *   William Hang
 *   Robert Delfin
 *   Mora Labisi
 */
package edu.cpp.cs.cs141.final_project;

import java.io.Serializable;

/**
 * This class represents the abstract concept of an item, which encompasses the
 * different power-ups the spy can obtain, as well as the briefcase, which is the
 * objective of the game. Each item has a location on the map.
 *
 * @author Diana Choi
 * @author Mora Labisi
 *
 */
public abstract class Item implements Serializable{

    final private ItemType TYPE;
    private boolean obtained;

    /**
     * This constructor will initialize the item.
     */
    public Item(ItemType type) {
        this.TYPE = type;
        obtained = false;
    }

    /**
     * This method will set obtained to true.
     */
    public void togglePickedUp(){
        obtained = true;
    }


    /**
     * @return The value of obtained
     */
    public boolean checkPickedUp(){
        return obtained;
    }

    /**
     * @return The ItemType
     */
    public ItemType getType(){
        return TYPE;
    }
}