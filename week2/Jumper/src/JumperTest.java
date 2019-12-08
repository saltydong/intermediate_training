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
import static org.junit.Assert.*;
import org.junit.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;

/**
 * This class runs a world that contains a bug and a rock, added at random
 * locations. Click on empty locations to add additional actors. Click on
 * populated locations to invoke methods on their occupants. <br />
 * To build your own worlds, define your own actors and a runner class. See the
 * BoxBugRunner (in the boxBug folder) for an example. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class JumperTest
{
	ActorWorld world = new ActorWorld();
	Jumper jumper = new Jumper();

	int num5 = 5;
	int num6 = 6;
	int num4 = 4;
	int num3 = 3;
	int num0 = 0;
	int num1 = 1;
	
	@Test
	public void testcanJump1() {
        world.add(new Location(num5, num5), jumper);  //(5,5)put a jumper (4, 5),(3, 5)is empty, return true
		assertEquals(true, jumper.canJump());
		world.remove(new Location(num5, num5));
	}

	@Test
	public void testcanJump2() {
		world.add(new Location(num5, num6), jumper);
        world.add(new Location(num4, num6), new Flower());  //(5,6)put a jumper (4, 6)is flower,(3, 3)is empty, return true
		assertEquals(true, jumper.canJump());
		world.remove(new Location(num5, num6));
		world.remove(new Location(num4, num6));
	}

	@Test
	public void testcanJump3() {
		world.add(new Location(num5, num6), jumper);
        world.add(new Location(num4, num6), new Rock());  //(5,6)put a jumper (4, 6)is Rock,(3, 3)is empty, return true
		assertEquals(true, jumper.canJump());
		world.remove(new Location(num5, num6));
		world.remove(new Location(num4, num6));
	}

	@Test
	public void testcanJump4() {
		world.add(new Location(num5, num6), jumper);
        world.add(new Location(num4, num6), new Bug());  //(5,6)put a jumper (4, 6)is Bug,(3, 6)is empty, return true
		assertEquals(false, jumper.canJump());
		world.remove(new Location(num5, num6));
		world.remove(new Location(num4, num6));
	}

	@Test
	public void testcanJump5() {
		world.add(new Location(num1, num1), jumper); //(1,1) two cells on the north has no space for jumper, return false
		assertEquals(false, jumper.canJump());
		world.remove(new Location(num1, num1));
	}

	@Test
	public void testcanJump6() {
		world.add(new Location(num0, num0), jumper); //(0,0)is the edge if the grid, jumper can't jump, return false
		assertEquals(false, jumper.canJump());
		world.remove(new Location(num0, num0));
	}

	@Test
	public void testcanJump7() {
		world.add(new Location(num5, num6), jumper);
        world.add(new Location(3, num6), new Bug());  //(5,6)put a jumper (3, 6)is Bug, return false
		assertEquals(false, jumper.canJump());
		world.remove(new Location(num5, num6));
		world.remove(new Location(num4, num6));
	}

	@Test
	public void testcanJump8() {
		world.add(new Location(num5, num6), jumper);
        world.add(new Location(3, num6), new Flower());  //(5,6)put a jumper (3, 6)is flower, return true
		assertEquals(true, jumper.canJump());
		world.remove(new Location(num5, num6));
		world.remove(new Location(num4, num6));
	}

}