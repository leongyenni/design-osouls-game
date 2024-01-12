package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.ResetManager;
import game.enums.Abilities;
import game.enums.Status;
import game.items.TokenOfSouls;
import game.terrains.Valley;

/**
 * An action that remove an actor from map and show that the actor
 * is killed.
 */
public class KilledAction extends Action {

    /**
     * Remove an actor from map and show that the actor is killed.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description to show that the actor is killed.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return actor + " is killed.";
    }

    /**
     * This action will not be shown in the menu.
     *
     * @param actor the name of actor
     * @return an empty string
     */
    @Override
    public String menuDescription(Actor actor) {
        return "";
    }
}
