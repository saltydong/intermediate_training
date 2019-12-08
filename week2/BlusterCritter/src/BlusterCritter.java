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
import info.gridworld.actor.Rock;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

import java.util.ArrayList;

/**
 * A <code>BlusterCritter</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class BlusterCritter extends Critter
{
    /**
     * Randomly selects a neighbor and changes this critter's color to be the
     * same as that neighbor's. If there are no neighbors, no action is taken.31
     */
    private int courage;

    public BlusterCritter(int c) {
        courage = c;
    }

    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> result = new ArrayList<Actor>();
        Location thisLocation = getLocation();
        Grid gr = getGrid();

        for (int i = thisLocation.getRow() - 2; i <= thisLocation.getRow() + 2; i++) {
            for (int j = thisLocation.getCol() - 2; j <= thisLocation.getCol() + 2; j++) {
                if (i != thisLocation.getRow() && j != thisLocation.getCol()) {
                    Location tempLocation = new Location(i, j);
                    if (gr.isValid(tempLocation)) {
                        Actor a = getGrid().get(tempLocation);
                        if (a != null) {
                            result.add(a);
                        }
                    }
                }
            }
        }

        return result;
    }

    public void processActors(ArrayList<Actor> actors)
    {
        if (actors.size() >= courage) {
            setColor(getColor().darker());
        }
        else {
            setColor(getColor().brighter());
        }
    }



    /**
     * Turns towards the new location as it moves.
     */
}