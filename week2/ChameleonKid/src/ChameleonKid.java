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
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 * @author Cay Horstmann
 */

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

import java.util.ArrayList;

/**
 * A <code>ChameleonKid</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class ChameleonKid extends Critter
{
    /**
     * Randomly selects a neighbor and changes this critter's color to be the
     * same as that neighbor's. If there are no neighbors, no action is taken.
     */
    public void processActors(ArrayList<Actor> actors)
    {
        int n = actors.size();
        if (n == 0){
            setColor(getColor().darker());
            return;
        }
        int r = (int) (Math.random() * n);

        Actor other = actors.get(r);
        setColor(other.getColor());
    }

    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> result = new ArrayList<Actor>();
        Actor actor1 = null;
        Actor actor2 = null;
        Grid grid = getGrid();
        Location thisLocation = getLocation();
        Location neighborLoc1 = thisLocation.getAdjacentLocation(getDirection() + Location.AHEAD);
        Location neighborLoc2 = thisLocation.getAdjacentLocation(getDirection() + Location.HALF_CIRCLE);
        
        if (grid.isValid(neighborLoc1)) {
            actor1 = getGrid().get(neighborLoc1);
        }
        if (grid.isValid(neighborLoc2)) {
            actor2 = getGrid().get(neighborLoc2);
        }

        if (actor1 != null) {
            result.add(actor1);
        }
        if (actor2 != null) {
            result.add(actor2);
        }

        return result;
        //return getGrid().getNeighbors(getLocation());
    }

    /**
     * Turns towards the new location as it moves.
     */
    public void makeMove(Location loc)
    {
        setDirection(getLocation().getDirectionToward(loc));
        super.makeMove(loc);
    }
}