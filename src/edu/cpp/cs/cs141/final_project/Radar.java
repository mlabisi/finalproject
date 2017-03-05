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
 * This class represents the power-up that grants the spy the ability
 * to see the room the briefcase is located in. It inherits the location
 * of the power-up from its superclass edu.cpp.cs.cs141.final_project.Item.
 *
 * @author Diana Choi
 *
 */
public class Radar extends PowerUp implements Serializable{

    /**
     * This constructor will create a PowerUp with the enum type appropriate
     * for a radar.
     */
    public Radar() {
        super(ItemType.RADAR);
    }

    /**
     * This method describes the power up's effect.
     *
     * @param player
     */
    @Override
    public void effect(ActiveAgent player) {
        super.togglePickedUp();
        player.toggleHasRadar(true);
    }

}