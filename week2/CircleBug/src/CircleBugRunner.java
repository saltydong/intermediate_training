 
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains box bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class CircleBugRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        CircleBug alice = new CircleBug(4);// this parameter may be sidelength
        alice.setColor(Color.ORANGE);
        //CircleBug bob = new CircleBug(3);
        world.add(new Location(2, 4), alice);
        //world.add(new Location(5, 5), bob);
        world.show();
    }
}