/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2002-2006 College Entrance Examination Board 
 * (http://www.collegeboard.com).
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
 * @author Alyce Brady
 * @author APCS Development Committee
 * @author Cay Horstmann
 */

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

import java.util.*;

/**
 * An <code>UnboundedGrid2</code> is a rectangular grid with an unbounded number of rows and
 * columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class UnboundedGrid2<E> extends AbstractGrid<E>
{
    private Object[][] occupantArray;
    private int num;
    /**
     * Constructs an empty unbounded grid.
     */
    public UnboundedGrid2()
    {
        num = 16;
        occupantArray = new Object[16][16];
    }

    public int getNumRows()
    {
        return num;
    }

    public int getNumCols()
    {
        return num;
    }

    public boolean isValid(Location loc)
    {
        if (loc.getRow() >=0 && loc.getCol() >= 0) {
            return true;
        }
        else {
        	return false;
        }
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < num; r++)
        {
            for (int c = 0; c < num; c++)
            {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, c);
                if (get(loc) != null) {
                    theLocations.add(loc);
                }
            }
        }

        return theLocations;
    }

    public E get(Location loc)
    {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }

        if (loc.getRow() < num && loc.getCol() < num) {
            return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
        }
        else {
         return null;
        }
    }

    public E put(Location loc, E obj)
    {
        if (loc == null) {
            throw new IllegalArgumentException("loc == null");
        }
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        if (obj == null) {
            throw new NullPointerException("obj == null");
        }

        if (loc.getRow() >= num || loc.getCol() >= num) {
            int doubleSize = num;
            while(loc.getRow() >= doubleSize || loc.getCol() >= doubleSize) {
                doubleSize = doubleSize * 2;
            }
            //if excceed the range, doubleSize = doubleSize * 2;

            Object[][] doubleArray = new Object[doubleSize][doubleSize];

            //remain the old ele in the ole gridword
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    doubleArray[i][j] = occupantArray[i][j];
                }
            }

            //resize the num of the world
            occupantArray = doubleArray;
            num = doubleSize;

        }
        // Add the object to the grid.
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }

    public E remove(Location loc)
    {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        
        if (loc.getRow() >= num || loc.getCol() >= num) {
         return null;
        }
        // Remove the object from the grid.
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }


}