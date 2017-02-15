package edu.cpp.cs.cs141.final_project;

/**
 * Created by chocolatecharme on 2/15/17.
 */
public class Gun extends Weapon {

    private boolean hasAmmo;

    public Gun(){
        super(true);
    }

    @Override
    public void attack(ActiveAgent target){
        target.decLives();
        hasAmmo = !hasAmmo;
    }

    public boolean toggleHasAmmo(){
        return !hasAmmo;
    }

    public boolean checkHasAmmo() {
        return hasAmmo;
    }
}
