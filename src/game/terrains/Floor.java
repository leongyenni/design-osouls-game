package game.terrains;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import game.enums.Abilities;
import game.enums.Status;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {

	/**
	 * Constructor.
	 */
	public Floor() {
		super('_');
	}

	/**
	 * Does not allow an enemy to step on floor as enemy cannot enter the Fire
	 * Shrine Link.
	 *
	 * @param actor the Actor to check
	 * @return false or actor cannot enter.
	 */
	@Override
	public boolean canActorEnter(Actor actor){ return actor.hasCapability(Status.HOSTILE_TO_ENEMY); }

}
