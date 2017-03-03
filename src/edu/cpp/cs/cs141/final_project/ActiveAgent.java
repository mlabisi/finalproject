package edu.cpp.cs.cs141.final_project;

import java.io.Serializable;

/**
 * CS 141: Intro to Programming and Problem Solving Professor: Edwin Rodriguez
 *
 * Final Project: Spy Game
 *
 * Create a small, yet interesting, text-based game involving a spy and ninjas.
 *
 * Team Magic!~<3 Diana Choi, William Hang, Logan Carichner, Robert Delfin, Mora
 * Labisi
 */


/**
 * 
 * @author Logan Carichner
 *
 */
public class ActiveAgent implements Serializable{
	private String agentName;
	private int agentHealth;
	private boolean hasRadar;
	private boolean isInvulnerable;
	private boolean isPlayer;
	private Weapon gun;

	/**
	 * This constructor takes no arguments and creates an enemy ninja assassin.
	 */
	public ActiveAgent() {
		agentName = "Ninja Assassin";
		agentHealth = 1;
		isPlayer = false;
		isInvulnerable = false;
	}

	/**
	 * This constructor takes a string argument and creates a player character.
	 * 
	 * @param name
	 *            the name of the character
	 */
	public ActiveAgent(String name) {
		agentName = name;
		agentHealth = 3;
		isInvulnerable = false;
		hasRadar = false;
		isPlayer = false;

		if (agentName.compareToIgnoreCase("player") == 0) {
			isPlayer = true;
			gun = new Weapon();
		}
	}

	public int getHP() {
		return agentHealth;
	}

	public int getAmmo() {
		return gun.getAmmo();
	}

	public void shoot() {
		gun.shoot();
	}

	public boolean isPlayer() {
		return isPlayer;
	}

	/**
	 * This method deals an amount of damage to the agent's HP.
	 * 
	 * @param x
	 *            the amount of damage
	 */
	public void takeDamage(int x) {
		agentHealth -= x;
	}

	/**
	 * This method asks the agent where it wants to move.
	 */
	public int agentMove() {
		int direction = Engine.roll(4);
		return direction;
	}

	public void toggleIsInvulnerable() {
		isInvulnerable = !isInvulnerable;
	}

	public void toggleHasRadar(boolean value) {
		hasRadar = !hasRadar;
	}

	public boolean checkHasRadar() {
		return hasRadar;
	}
	
	public void pickUpAmmo() {
		gun.pickUpAmmo();
	}
}