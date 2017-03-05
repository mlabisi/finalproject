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