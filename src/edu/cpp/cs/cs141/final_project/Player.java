package edu.cpp.cs.cs141.final_project;

/**
 * Created by chocolatecharme on 2/13/17.
 */
public class Player extends ActiveAgent {

    private String name;

    private int lives;

    public Player(String name){
        super(AgentType.PLAYER);
        this.name = name;
        lives = 3;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void incLives(){
        ++lives;
    }

    public void decLives(){
        --lives;
    }

    public int getLives(){
        return lives;
    }
}
