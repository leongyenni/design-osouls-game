package game.actors.enemies;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.actions.KilledAction;
import game.actions.ResetPositionAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;
import game.interfaces.Soul;



import java.util.ArrayList;

/**
 * A class that represents an enemy type.
 */
public abstract class Enemy extends Actor implements Soul, Resettable {
    /**
     * Enemy has a list of behaviours.
     */
    protected ArrayList <Behaviour> behaviours = new ArrayList<>();

    /**
     * Enemy has a follow behaviour to follow the player.
     */
    protected FollowBehaviour followBehaviour;

    private Location initialLocation;


    /**
     * Constructor.
     *
     * @param name        the name of the Enemy
     * @param displayChar the character that will represent the Enemy in the display
     * @param hitPoints   the Enemy's starting hit points
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);

        this.behaviours.add(new AttackBehaviour());

        // add enemy to resettable list
        this.registerResetInstance();
    }

    /**
     * Returns a collection of the Actions that the otherActor can do to the Player.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions actions = new Actions();

		if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
		    if(!otherActor.hasCapability(Status.DISARMED)){
                actions.add(new AttackAction(this,direction));
                actions.add(otherActor.getWeapon().getActiveSkill(this, direction));
            }

            if(followBehaviour == null){
                followBehaviour = new FollowBehaviour(otherActor);
                this.behaviours.add(1, followBehaviour);
            }

		    this.addCapability(Status.HOSTILE_TO_PLAYER);
		}

		return actions;
	}

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Enemy
     * @param lastAction The Action this Enemy took last turn.
     * @param map        the map containing the Enemy
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if(!this.isConscious()){
            return new KilledAction();
        }

        if(this.hasCapability(Status.RESET)){
            this.removeCapability(Status.RESET);
            return new ResetPositionAction(initialLocation);
        }

        // loop through all behaviours
        for(Behaviour Behaviour : behaviours) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }

        for (Action action: actions){
            if(this.hasCapability(Abilities.RANGED_ATTACK)){
                this.removeCapability(Abilities.RANGED_ATTACK);
                return action;
            }
        }

        return new DoNothingAction();
    }

    /**
     * Transfer a certain number of souls to another Soul instance.
     * Each enemy will transfer different amount of souls.
     * Override this method to transfer different number of souls to another Soul instance.
     *
     * @param soulObject a target souls
     */
    @Override
    public abstract void transferSouls(Soul soulObject);


    /**
     * Reset enemy's position, health and skills.
     */
    @Override
    public void resetInstance() {
        this.hitPoints = maxHitPoints;
        this.removeCapability(Status.HOSTILE_TO_PLAYER);
        this.behaviours.remove(followBehaviour);
        this.followBehaviour = null;
        this.addCapability(Status.RESET);
    }

    /**
     * Enemy will need to be reset every time a reset manager runs.
     * So, enemy cannot be removed from the reset list.
     *
     * @return true
     */
    @Override
    public boolean isExist() {
        return true;
    }

    /**
     * Display the name, hit points, maximum hit points and weapon of an enemy.
     *
     * @return a description of an enemy.
     */
    @Override
    public String toString(){
        return name + "(" + hitPoints + "/" + maxHitPoints + ")(" + getWeapon() + ")";
    }

    /**
     * Store the initial location of the enemy.
     *
     * @param location the location of the ground
     */
    public void storeLocation(Location location){
        this.initialLocation = location;
    }


}
