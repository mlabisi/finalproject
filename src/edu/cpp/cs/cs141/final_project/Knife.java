package edu.cpp.cs.cs141.final_project;

/**
 * Created by chocolatecharme on 2/15/17.
 */
public class Knife extends Weapon {

    public Knife(){
        super(true);
    }

    @Override
    public void attack(ActiveAgent target){
        target.decLives();
    }
}
