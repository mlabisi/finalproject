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