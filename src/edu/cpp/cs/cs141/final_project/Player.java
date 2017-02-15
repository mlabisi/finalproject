package edu.cpp.cs.cs141.final_project;

/**
 * Created by chocolatecharme on 2/13/17.
 */
public class Player extends ActiveAgent {

    private String name;

    private Gun gun;

    private int turns;

    private boolean isInvincible;

    public Player(){
        super(AgentType.PLAYER, 3);
        gun = new Gun();
        turns = 0;
        isInvincible = false;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public Gun getGun(){
        return gun;
    }

    public void incTurns() {
        ++turns;
    }

    public int getTurns(){
        return turns;
    }

    public boolean checkIsInvincible() {
        return isInvincible;
    }

    public void toggleInvincible() {
        isInvincible = !isInvincible;
    }
}
