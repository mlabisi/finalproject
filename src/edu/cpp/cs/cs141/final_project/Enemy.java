package edu.cpp.cs.cs141.final_project;

/**
 * Created by chocolatecharme on 2/13/17.
 */
public class Enemy extends ActiveAgent {

    private Knife knife;

    public Enemy(){
        super(AgentType.ENEMY, 1);
        knife = new Knife();
    }


}
