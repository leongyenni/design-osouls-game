package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * An action to reset actor position.
 */
public class ResetPositionAction extends Action {
    private Location resetLocation;

    /**
     * Constructor.
     *
     * @param location the location of the ground
     */
    public ResetPositionAction(Location location){
        this.resetLocation = location;
    }

    /**
     * Reset the location of an actor.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description to show that the actor has been reset to its original location
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.moveActor(actor,resetLocation);
        return actor + " has been reset to original location.";
    }

    /**
     * This action will not be shown in menu.
     *
     * @param actor The actor performing the action.
     * @return null
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
