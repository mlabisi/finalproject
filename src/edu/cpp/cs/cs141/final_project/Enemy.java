package edu.cpp.cs.cs141.final_project;

/**
 * Created by chocolatecharme on 2/13/17.
 */
public class Enemy extends ActiveAgent {

    private Weapon knife;
    public Enemy(){
        super(AgentType.ENEMY);
        knife = new Weapon()
    }

    @Override
    public void attack(ActiveAgent target){
        ((Player)target).decLives();
    }
}
