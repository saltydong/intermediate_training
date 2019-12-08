
import info.gridworld.actor.*;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	public Location next;
	public Location last;
	public boolean isEnd = false;
	public Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
	public Integer stepCount = 0;
	boolean hasShown = false;
	public Stack<Location> wayOut = new Stack<Location>();
	// wayOut is to show the way out of the maze without repeated steps
	
	private int[] dirTime = {1, 1, 1, 1};

	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		setColor(Color.GREEN);
		last = new Location(0, 0);
		next = null;
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
		// initialize the start location
		if (stepCount == 0)
		{			
			ArrayList<Location> temp = new ArrayList<Location>();
			temp.add(getLocation());
			crossLocation.push(temp);		
			wayOut.push(getLocation());
		}
		
		boolean willMove = canMove();
		if (isEnd) {
			// if ended, show the message of the stepCount and set the flowers to be green.
			if (!hasShown) {
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
			for (Location fl: wayOut) {
				getGrid().get(fl).setColor(Color.GREEN);
			}			
		} else {
			// if not ended, decide which way to go
			if (willMove) {
				move();
				stepCount++;
			} else {
				//if can not move, then turn back
				turnBack();
			}
		}
			
	}

	/**
	 * Find all positions that can be move to.
	 * 
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return null;
		ArrayList<Location> valid = new ArrayList<Location>();
		
		int[] dir = {Location.NORTH, Location.EAST, Location.SOUTH, Location.WEST};
		for (int i = 0; i < 4; i++)
		{
			Location round = loc.getAdjacentLocation(dir[i]);
			if (gr.isValid(round)) {
				Actor obj = gr.get(round);
				if (obj == null) {
					// the location next to it is empty
					valid.add(round);
				}
				else if (obj instanceof Rock && obj.getColor().equals(Color.RED)) {
					// the location next to it is the RED STONE, which is the end
					next = round;
					valid.clear();
					valid.add(next);
					break;
				}
			}
		}		
		return valid;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * 
	 * @return true if this bug can move.
	 */
	public boolean canMove() {
		Location current = getLocation();
		ArrayList<Location> valid = new ArrayList<Location>();
		
		// get the valid location around the current location
		valid = getValid(current);
		if (valid.size() == 0) {
			return false;
		}
		else {
			return true;
		}
	}
	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null) {
			return;
		}
		Location loc = getLocation();		
		int dir = selectDir(loc);
		dirTime[dir/90]++;
		
		if (gr.isValid(next)) {
			Actor obj = gr.get(next);
			if (obj instanceof Rock && obj.getColor().equals(Color.RED)) {
				isEnd = true;
			}
			setDirection(loc.getDirectionToward(next));
			moveTo(next);
			wayOut.push(next);
			
			// Add the "next location" to the Top of the Stack to record what directions have been to
			crossLocation.peek().add(next);
			
			// Push a ArrayList to the Stack, which only contains the "next location", to record every step the bug move
			ArrayList<Location> temp = new ArrayList<Location>();
			temp.add(next);
			crossLocation.push(temp);
			
		} else {
			removeSelfFromGrid();
		}
		
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}
	
	private void turnBack() {		
		Grid<Actor> gr = getGrid();
		if (gr == null) {
			return;
		}
		
		if (!crossLocation.empty()) {
			crossLocation.pop();
			wayOut.pop();
		}
		
		if (crossLocation.empty()) {
			return;
		}
		
		Location loc = getLocation();
		Location back = crossLocation.peek().get(0);			
		int dir = loc.getDirectionToward(back);	
		dirTime[(dir/90 + 2) % 4]--;
		
		if (gr.isValid(back)) {
			setDirection(dir);
			moveTo(back);
			stepCount++;
		} else {
			removeSelfFromGrid();
		}
		
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);		
		
	}
	
	// select direction basing on the direction selected times
	private int selectDir(Location loc) {
		ArrayList<Location> nextLocation = getValid(loc);		

		/* Sum is the number of all steps of all directions
		 * The probability of each direction is:  
		 * 	Steps-of-corresponding-direction / All-steps-ofall-directions
		 */		
		int sum = 0;
		Location round = null;
		int dir = 0;		
		int i = 0;
		for (i = 0; i < nextLocation.size(); i++)
		{
			round = nextLocation.get(i);
			dir = loc.getDirectionToward(round);
			sum += dirTime[dir/90];
		}	
		
		int rd = (int)(Math.random() * sum);		
		int upperBound = 0;
		
		for (i = 0; i < nextLocation.size(); i++)
		{
			round = nextLocation.get(i);
			dir = loc.getDirectionToward(round);
			upperBound += dirTime[dir/90];
			if (rd <= upperBound) {
				break;
			}
		}	

		next = nextLocation.get(i);	
		return dir;
	}
}





