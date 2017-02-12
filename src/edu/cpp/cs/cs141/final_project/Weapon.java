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
 */
public class Weapon {
	/**
	 * This is the constructor for the weapon. 
	 */
	public Weapon(){
	}
	/**
	 * 	This represents whether or not the 
	 * agent has ammo in the gun.
	 */
	private boolean hasAmmo;
	/**
	 *  This represents the maximum ammunition
	 *  the agent can have
	 */
	private int maxAmmo;
	/**
	 * This method will kill the ninja-assassin if the 
	 * bullet hits the ninja-assassin.
	 */
	public void killEnemy(){
		
	}
	/**
	 * This represents the action of the ninja-assassin
	 * attacking the agent.
	 */
	public int stabAgent(int agentHealth){
       		return agentHealth;
	}
}
