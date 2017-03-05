/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodr�guez
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
 * This class represents the weapons in the game which includes the
 * attributes and behaviors of the weapons. The agent will have a pistol, 
 * and the ninja-assassin will have a knife.
 *
 * @author Mora Labisi
 */
public class Weapon implements Serializable{
	private boolean hasAmmo;
	private int ammo;

	/**
	 * This default constructor initializes the weapon.
	 */
	public Weapon() {
		hasAmmo = true;
		ammo = 1;
	}

	/**
	 * @return The amount of ammo left
	 */
	public int getAmmo() {
		return ammo;
	}

	/**
	 * Called when an extra bullet is picked up.
	 */
	public void pickUpAmmo() {
		ammo = 1;
		hasAmmo = true;
	}

	/**
	 * This method will update class fields when the player shoots.
	 */
	public void shoot() {
		ammo = 0;
		hasAmmo = false;
	}
	
}
