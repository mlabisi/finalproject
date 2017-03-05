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

import java.io.Serializable;

/**
 * This class is a {@link Square} that represents a
 * hallway in the game. The hallway can either be
 * clear or there can be ninjas present.
 *
 * @author Mora Labisi
 * @author Logan Carichner
 */
public class Hallway extends Square implements Serializable {
    private boolean isClear;
    private boolean isEntrance;
    private boolean hasAgent;
    private boolean hasPlayer;
    private boolean hasItem;
    private boolean restricted = false;
    private ActiveAgent agent;
    private Item item;
    private char symbol;

    /**
     * This is the constructor for the hallway class.
     */
    public Hallway() {
        isClear = true;
        isEntrance = false;
        setType("Hallway");
    }


    /**
     * This method will place the player on this square.
     */
    @Override
    public void placeSpy() {
        agent = new ActiveAgent("player");
        isClear = false;
        hasAgent = true;
        hasPlayer = true;
    }


    /**
     * This method will tell whether or not this square has an item.
     *
     * @return Value of hasItem
     */
    public boolean hasItem() {
        return hasItem;
    }

    /**
     * This method will toggle the value of hasItem.
     */
    public void toggleHasItem() {
        hasItem = !hasItem;
    }

    /**
     * This method will initialize agent and place it on this square.
     */
    @Override
    public void placeAgent() {
        agent = new ActiveAgent();
        isClear = false;
        hasAgent = true;
    }

    /**
     * This method will get the agent's health.
     *
     * @return The agent's health
     */
    public int getAgentHealth() {
        return agent.getHP();
    }

    /**
     * This method will place an already made agent onto this square.
     *
     * @param ninja The agent to be placed
     */
    @Override
    public void placeAgent(ActiveAgent ninja) {
        agent = ninja;
        if (agent.isPlayer())
            hasPlayer = true;
        isClear = false;
        hasAgent = true;
    }

    /**
     * @return The value of hasAgent
     */
    @Override
    public boolean hasAgent() {
        return hasAgent;
    }

    /**
     * @return The value of hasPlayer
     */
    @Override
    public boolean hasPlayer() {
        return hasPlayer;
    }

    /**
     * @param string The type to be assigned to the square.
     */
    @Override
    public void setType(String string) {
        squareType = string;
    }

    /**
     * This method sets the value of isEntrance to true.
     */
    @Override
    public void isEntrance() {
        isEntrance = true;
    }

    /**
     * @return The value of isEntrance.
     */
    @Override
    public boolean checkEntry() {
        return isEntrance;
    }

    /**
     * Implements the hasBriefcase method from square.
     */
    @Override
    public void hasBriefcase() {
    }

    /**
     * @return The value of {@link #isClear}
     */
    @Override
    public boolean checkIsClear() {
        return isClear;
    }

    /**
     * This method changes the hallway's isClear boolean to the opposite of its current value
     */
    public void clear() {
        isClear = !isClear;
    }

    /**
     * This method checks whether the hallway is considered an entrance
     *
     * @return the value of isEntrance
     */
    public boolean checkIsEntrance() {
        return isEntrance;
    }

    /**
     * This method sets the hallway as an entrance.
     */
    public void setEntrance() {
        isEntrance = true;
    }

    /**
     * @param item The item to be placed
     */
    @Override
    public void place(Item item) {
        this.item = item;
        symbol = item.getType().toChar();
        hasItem = true;
    }

    /**
     * This method applies the power up's effect.
     */
    public void useItem() {
        ((PowerUp) item).effect(agent);
        item = null;
        hasItem = false;
    }

    /**
     * This method overrides the abstract square's method, and returns the character to display.
     */
    @Override
    public char getSymbol() {
        if (hasPlayer) {
            return 80;
        } else if (hasAgent && super.lightsOn()) {
            return 78;
        } else if (hasItem && !item.checkPickedUp() && super.lightsOn()) {
            return symbol;
        } else if (super.lightsOn()) {
            return 32;
        }
        return 42;
    }

    /**
     * This method is used to restrict the hallways within 3 squares of the
     * player's initial position.
     */
    @Override
    public void restrict() {
        restricted = true;
    }

    /**
     * @return The value of restricted
     */
    @Override
    public boolean isOffLimits() {
        return restricted;
    }

    /**
     * @return The active agent
     */
    @Override
    public ActiveAgent getAgent() {
        return agent;
    }

    /**
     * This method is called when a ninja has been shot.
     */
    @Override
    public void deleteAgent() {
        agent = null;
        isClear = true;
        hasAgent = false;
        hasPlayer = false;
    }

    /**
     * @return The direction the ninja will move
     */
    @Override
    public int askANinja() {
        return agent.agentMove();
    }

    /**
     * @return The item
     */
    public Item getItem() {
        return item;
    }
}
