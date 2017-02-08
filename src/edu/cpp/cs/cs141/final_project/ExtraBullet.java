package edu.cpp.cs.cs141.final_project; /**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodr�guez
 *
 * Programming Assignment #4
 *
 * A simple text-based spy game in which the spy, controlled by the player, tries to find
 * the briefcase before the ninja-assassins find and kill the spy.
 *
 * Team Magic!~<3
 *   Diana Choi
 *   Logan Carichner
 *   William Hang
 *   Robert Delfin
 *   Mora Labisi
 */

/**
 * This class represents the power-up that grants the spy an extra bullet to reload
 * the gun, if the gun magazine is empty. If the magazine is full, this power-up has
 * no effect.
 * 
 * @author Diana Choi
 *
 */
public class ExtraBullet extends PowerUp{
	
	/**
	 * @param row
	 * @param col
	 */
	public ExtraBullet(int row, int col) {
		super(row, col);
	}

	/* (non-Javadoc)
	 * @see edu.cpp.cs.cs141.final_project.edu.cpp.cs.cs141.final_project.PowerUp#effect()
	 */
	@Override
	public void effect() {
		//++bullets in gun
		//if bullets > 1, set bullets = 1
	}

}
