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

/**
 * A <code>SparseBoundedGrid</code> is a rectangular grid with a finite number of
 * rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class SparseBoundedGrid<E> extends AbstractGrid<E>
{
    private SparseGridNode[] occupantArray; // the array storing the grid elements

    private int numCols;
    private int numRows;
    /**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * @param rows number of rows in SparseBoundedGrid
     * @param cols number of columns in SparseBoundedGrid
     */
    public SparseBoundedGrid(int rows, int cols)
    {
        if (rows <= 0) {
            throw new IllegalArgumentException("rows <= 0");
        }
        if (cols <= 0) {
            throw new IllegalArgumentException("cols <= 0");
        }

        numCols = cols;
        numRows = rows;
        occupantArray = new SparseGridNode[rows];
    }

    public int getNumRows()
    {
        return numRows;
    }

    public int getNumCols()
    {
        // Note: according to the constructor precondition, numRows() > 0, so
        // theGrid[0] is non-null.
        return numCols;
    }

    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < getNumRows(); r++)
        {
            for (SparseGridNode c = occupantArray[r]; c != null; c = c.getNext())
            {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, c.getColumn());
                theLocations.add(loc);
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

        for (SparseGridNode c = occupantArray[loc.getRow()]; c != null; c = c.getNext())
        {
            // If there's an object at this location, put it in the array.
            if (c.getColumn() == loc.getCol()) {
            	return (E) c.getOccupant();
            }
        }

        return null; // unavoidable warning
    }

    public E put(Location loc, E obj)
    {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        if (obj == null) {
            throw new NullPointerException("obj == null");
        }

        // Add the object to the grid.
        E oldOccupant = get(loc);

        SparseGridNode c = occupantArray[loc.getRow()];

        //put the ele on the top of the list
        occupantArray[loc.getRow()] = new SparseGridNode(obj, loc.getCol(), c);


        return oldOccupant;
    }

    public E remove(Location loc)
    {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        
        // Remove the object from the grid.
        E r = get(loc);

		SparseGridNode c = null;
		SparseGridNode c1 = null;
        for (c = occupantArray[loc.getRow()]; c.getColumn() != loc.getCol(); c = c.getNext())
        {
            // If there's an object at this location, put it in the array.
        	c1 = c;
        }  
        if (c1 != null) {
        	c1.setNext(c.getNext());
        }

        return r;
    }
}