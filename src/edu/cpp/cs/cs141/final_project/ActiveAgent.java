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

public class ActiveAgent implements Serializable{

	/**
	 * This is a {@code String} that holds the name of {@code this}
	 * {@link ActiveAgent}.
	 */
	private String agentName;

	/**
	 * This is an {@code int} representation of {@code this}
	 * {@link ActiveAgent}'s health. Ninjas have {@code 1} and
	 * agents {@code 3}.
	 */
	private int agentHealth;
	private boolean hasRadar;
	private boolean isInvulnerable;
	private boolean isPlayer;
	private Weapon gun;
	private int invulCount;

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
	 * @param name The name of the character
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

	/**
	 * This method will return the agent's health
	 *
	 * @return The agent's health
	 */
	public int getHP() {
		return agentHealth;
	}

	/**
	 * This method will return the amount of ammo in the
	 * agent's gun.
	 *
	 * @return The amount of ammo in the gun
	 */
	public int getAmmo() {
		return gun.getAmmo();
	}

	/**
	 * This method will call the shoot method on the gun.
	 */
	public void shoot() {
		gun.shoot();
	}

    /**
     * This method will tell whether or not the active agent is a
     * player.
     *
     * @return The value of isPlayer
     */
    public boolean isPlayer() {
		return isPlayer;
	}

	/**
	 * This method deals an amount of damage to the agent's HP.
	 * 
	 * @param x The amount of damage
	 */
	public void takeDamage(int x) {
		agentHealth -= x;
	}

	/**
	 * This method asks the agent where it wants to move. It is
     * used for the AI.
	 */
	public int agentMove() {
		int direction = Engine.roll(4);
		return direction;
	}

    /**
     * This method toggles the value of isInvulnerable.
     */
    public void toggleIsInvulnerable() {
		isInvulnerable = !isInvulnerable;
	}

    /**
     * This method will change the value of hasRadar.
     *
     * @param value Boolean value to be assigned
     */
    public void toggleHasRadar(boolean value) {
		hasRadar = value;
	}

    /**
     * This method will check the value of hasRadar
     *
     * @return The value of hasRadar
     */
    public boolean checkHasRadar() {
		return hasRadar;
	}

    /**
     * This method will call the pickUpAmmo method on the gun
     */
    public void pickUpAmmo() {
		gun.pickUpAmmo();
	}

    /**
     * This method will set the number that the counter will
     * begin at for the Invulnerability power up.
     *
     * @param num The number to be assigned
     */
    public void setInvul(int num){
		invulCount = num;
	}

    /**
     * This method will run the counter for the Invulnerability
     * power up.
     */
    public void tickInvul(){
		invulCount -= 1;
	}

    /**
     * This method will tell the current value of the counter for
     * the Invulnerability power up.
     *
     * @return The value of invulCount
     */
    public int getInvul(){
		return invulCount;
	}

    /**
     * This method will check the value of isInvulnerable.
     *
     * @return The value of isInvulnerable
     */
    public boolean checkHasInvul(){
		return isInvulnerable;
	}
	
}