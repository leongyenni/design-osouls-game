package game.terrains;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.enums.Abilities;
import game.enums.Status;

/**
 * A class that represents a valley.
 * The gorge or endless gap that is dangerous for the Player.
 */
public class Valley extends Ground {

	/**
	 * Constructor.
	 */
	public Valley() {
		super('+');
	}

	/**
	 * Only allow player to step on the valley as player can fall from valley.
	 *
	 * @param actor the Actor to check
	 * @return false if actor has no FALL Abilities
	 */
	@Override
	public boolean canActorEnter(Actor actor){
		return actor.hasCapability(Abilities.FALL);
	}


	/**
	 * When an Actor steps on Valley, the Actor will fall from the cliff.
	 *
	 * @param location The location of the Ground
	 */
	@Override
	public void tick(Location location) {
		if(location.containsAnActor()){
			location.getActor().hurt(Integer.MAX_VALUE);
			location.getActor().addCapability(Status.FALLEN);
		}
	}
}
