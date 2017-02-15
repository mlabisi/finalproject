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
 * This class represents the weapons in the game which includes the
 * attributes and behaviors of the weapons. The agent will have a pistol, 
 * and the ninja-assassin will have a knife.
 *
 * @author William Hang
 * @author Mora Labisi
 */
public abstract class Weapon {
    /**
     * 	This represents whether or not the
     * agent has ammo in the gun.
     */
    private boolean hasAmmo;

    /**
	 * This is the constructor for the weapon. 
	 */
	public Weapon(boolean hasAmmo){
	    this.hasAmmo = hasAmmo;
	}

    /**
     * Simulates the stabbing/shooting of an enemy
     *
     * @param target
     */
    public abstract void attack(ActiveAgent target);


}
