/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Cay Horstmann
 */


import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Bug;

import java.awt.Color;

/**
 * A <code>Jumper</code> is an actor that can jump and turn. It drops flowers as
 * it moves. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class Jumper extends Actor
{
    /**
     * Constructs a red bug.
     */
    public Jumper()
    {
        setColor(Color.RED);
    }

    /**
     * Constructs a bug of a given color.
     * @param bugColor the color for this bug
     */
    public Jumper(Color bugColor)
    {
        setColor(bugColor);
    }

    /**
     * Moves if it can jump, turns otherwise.
     */
    public void act()
    {
        if (canJump()) {
            jump();
        }
        else {
            turn();
        }
    }

    /**
     * Turns the bug 45 degrees to the right without changing its location.
     */
    public void turn()
    {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }

    /**
     * Moves the bug forward, putting a flower into the location it previously
     * occupied.
     */
    public void jump()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location next2 = next.getAdjacentLocation(getDirection());
        if (gr.isValid(next2)) {
            moveTo(next2);
        }
        else {
            removeSelfFromGrid();
        }
    }

    /**
     * Tests whether this bug can jump forward into a location that is empty or
     * contains a flower.
     * @return true if this bug can jump.
     */
    public boolean canJump()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return false;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location next2 = next.getAdjacentLocation(getDirection());
        if (!gr.isValid(next)) {
            return false;
        }
        if (!gr.isValid(next2)) {
        	return false;
        }
        Actor neighbor = gr.get(next);
        Actor neighbor2 = gr.get(next2);
        if ((neighbor == null) || (neighbor instanceof Flower) || (neighbor instanceof Rock)) {
        	if ((neighbor2==null) || (neighbor2 instanceof Flower)) {
        		return true;
        	}
        	else {
             return false;
            }
        }
        else {
         return false;
        }
        // ok to jump into empty location or onto flower
        // not ok to jump onto any other actor
    }
}