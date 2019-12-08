import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;

public class UnboundedGrid2Runner {
	public static void main(String[] args) {
		ActorWorld world = new ActorWorld(); //new a new world for test
		world.addGridClass("UnboundedGrid2");
		world.add(new Location(3, 4), new Critter());
		world.show();
	}
}