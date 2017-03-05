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
 * This class represents the abstract concept of a power-up the spy
 * can obtain during the game, and includes the location, inherited
 * by the superclass edu.cpp.cs.cs141.final_project.Item, and a method for each type's effect.
 *
 * @author Diana Choi
 * @author Mora Labisi
 *
 */
public abstract class PowerUp extends Item implements Serializable{


    /**
     * This constructor will create a PowerUp with the appropriate ItemType.
     */
    public PowerUp(ItemType type) {
        super(type);
    }

    /**
     * This method describes the power up's effect.
     */
    public abstract void effect(ActiveAgent player);



}