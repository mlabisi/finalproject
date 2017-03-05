/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodr&iacute;guez
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
 * This class represents the power-up that grants the spy an extra bullet to reload
 * the gun, if the gun magazine is empty. If the magazine is full, this power-up has
 * no effect.
 *
 * @author Diana Choi
 *
 */

public class ExtraBullet extends PowerUp implements Serializable{

    /**
     * This method will create a PowerUp with the enum type appropriate
     * for an extra bullet.
     */
    public ExtraBullet() {
        super(ItemType.EXTRA_BULLET);
    }

    /**
    * This method describes the power up's effect.
    */
    @Override
    public void effect(ActiveAgent player) {
        if(player.getAmmo() < 1)
            player.pickUpAmmo();
        super.togglePickedUp();
    }

}
