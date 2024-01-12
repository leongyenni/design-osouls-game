package game.actors.enemies;


import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.actions.KilledAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Resettable;
import game.interfaces.Soul;

import java.util.Random;

/**
 * A class that represents an undead minion.
 */
public class Undead extends Enemy {
	private Random rand = new Random();

	/** 
	 * Constructor.
	 * All Undead are represented by an 'u' and have 30 hit points.
	 *
	 * @param name the name of this Undead
	 */
	public Undead(String name) {
		super(name, 'u', 50);
		this.behaviours.add(new WanderBehaviour());
	}

	/**
	 * Undead does not hold any weapon.
	 * His base attack points are 20 hit points.
	 *
	 * @return a IntrinsicWeapon
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(20, "punches");
	}


	/**
	 * Figure out what to do next.
	 *
	 * @see Actor#playTurn(Actions, Action, GameMap, Display)
	 * @param actions    collection of possible Actions for this Undead
	 * @param lastAction The Action this Undead took last turn.
	 * @param map        the map containing the Undead
	 * @param display    the I/O object to which messages may be written
	 * @return the Action to be performed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {

		// When it is not under attack, or not following a Player, Undead has 10% chance to die instantly every turn
		if (rand.nextInt(100) <= 10 && !this.hasCapability(Status.HOSTILE_TO_PLAYER)){
			return new KilledAction();
		}

		return super.playTurn(actions,lastAction,map,display);
	}

	/**
	 * Undead are removed from map immediately when the world is resetting.
	 */
	@Override
	public void resetInstance() {
		this.hurt(hitPoints);
	}

	/**
	 * Transfers 50 souls to Player when he is killed by the Player.
	 *
	 * @param soulObject a target souls
	 */
	@Override
	public void transferSouls(Soul soulObject) {
		soulObject.addSouls(50);
	}

	/**
	 * Display the name, hit points, maximum hit points of Undead.
	 *
	 * @return a description of an enemy.
	 */
	@Override
	public String toString(){
		return name + "(" + hitPoints + "/" + maxHitPoints + ")(no weapon)";
	}


}
