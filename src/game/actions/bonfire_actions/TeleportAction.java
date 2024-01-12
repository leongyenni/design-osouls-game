package game.actions.bonfire_actions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;
import game.terrains.Bonfire;

/**
 * A class to teleport player from one bonfire to another
 */
public class TeleportAction extends MoveActorAction {
    private Bonfire bonfire;

    /**
     * Constructor.
     *
     * @param moveToLocation Location to move to
     * @param direction String describing the direction to move in, e.g. "north"
     * @param bonfire the bonfire to move to
     */
    public TeleportAction(Location moveToLocation, String direction, Bonfire bonfire) {
        super(moveToLocation, direction);
        this.bonfire = bonfire;
    }

    /**
     * Allow player to move to another bonfire.
     *
     * @see MoveActorAction#execute(Actor, GameMap)
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description suitable for TeleportAction
     */
    public String execute(Actor actor, GameMap map) {
        bonfire.saveLocation(moveToLocation);
        return super.execute(actor, map);
    }
}
