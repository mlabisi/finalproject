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
 * This class represents the active agents in the spy game and the
 * attributes it contains.
 */
public abstract class ActiveAgent {

    /**
     * The appropriate {@link Weapon} for the agent.
     */
    private Weapon weapon;

    private AgentType type;

    private int lives;

    public ActiveAgent(AgentType type, int lives){
        this.type = type;
        this.lives = lives;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void decLives(){
        --lives;
    }

}
