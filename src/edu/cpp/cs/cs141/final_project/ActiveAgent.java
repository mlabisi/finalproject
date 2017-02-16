package edu.cpp.cs.cs141.final_project;

/**
 * CS 141: Intro to Programming and Problem Solving Professor: Edwin Rodríguez
 *
 * Final Project: Spy Game
 *
 * Create a small, yet interesting, text-based game involving a spy and ninjas.
 *
 * Team Magic!~<3 Diana Choi, William Hang, Logan Carichner, Robert Delfin, Mora
 * Labisi
 */
public class ActiveAgent {
	private boolean alive;
	private String agentName;
	private int agentHealth;
	private int positionX;
	private int positionY;

	/**
	 * This constructor takes no arguments and creates an enemy ninja assassin.
	 */
	public ActiveAgent() {
		alive = true;
		agentName = "Ninja Assassin";
		agentHealth = 1;
		positionX = Board.verifyPosition(Engine.random(Board.getSize()));
		positionY = Board.verifyPosition(Engine.random(Board.getSize()));

	}

	/**
	 * This constructor takes a string argument and creates a player character.
	 * 
	 * @param name
	 *            the name of the character
	 */
	public ActiveAgent(String name) {
		alive = true;
		agentName = name;
		agentHealth = 3;
		positionX = 9;
		positionY = 0;
	}

	/**
	 * This method deals an amount of damage to the agent's HP.
	 * 
	 * @param x
	 *            the amount of damage
	 */
	public void takeDamage(int x) {
		agentHealth -= x;
		checkAlive();
	}

	/**
	 * This method checks the Agent's health and sets it's alive marker to false
	 * if they have 0 or less health
	 */
	public void checkAlive() {
		if (agentHealth <= 0)
			alive = false;
	}

/**
 * This method changes the agent's position.
 * @param direction a string representing a direction of movement
 */
	public void agentMove(String direction) {
		//Each case in this switch also verifies that the X or Y position values are within the range of the board
		switch (direction) {
		case "up":
			positionY = (positionY > 0) ? positionY -= 1 : 0;
			break;
		case "down":
			positionY = (positionY < 8) ? positionY += 1 : 8;
			break;
		case "left":
			positionX = (positionX > 0) ? positionX -= 1 : 0;
			break;
		case "right":
			positionX = (positionX < 8) ? positionX += 1 : 8;
			break;
		default:
			UI.callException("Motion");
			break;
		}
		

}
