/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodriguez
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
package edu.cpp.cs.cs141.final_project;

import java.io.Serializable;

/**
 * This class represents the {@link Briefcase} {@link Item}.
 *
 * @author Mora Labisi
 *
 */
public class Briefcase extends Item implements Serializable{

    /**
     *
     */
    public Briefcase() {
        super(ItemType.BRIEFCASE);
    }

}