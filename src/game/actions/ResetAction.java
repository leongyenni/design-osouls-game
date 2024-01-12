package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.ResetManager;
import game.enums.Status;
import game.items.TokenOfSouls;

/**
 * An action that executes RESET feature when the actor dies.
 */
public class ResetAction extends Action {

    /**
     * When the actor dies, reset manager is run to execute RESET feature.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description to show if the player is killed or falls from valley, and show that
     *         the world is resetting.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        ResetManager.getInstance().run();

        if(actor.hasCapability(Status.FALLEN)){ //actor falls from cliff
            actor.removeCapability(Status.FALLEN);
            result += actor + " falls from the valley.\n";
        }
        else{
            result += actor + " is killed.\n";
        }

        result += "The world is resetting ...";
        return result;
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
