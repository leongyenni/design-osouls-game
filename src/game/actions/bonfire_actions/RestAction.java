package game.actions.bonfire_actions;

import edu.monash.fit2099.engine.*;
import game.ResetManager;
import game.terrains.Bonfire;

/**
 * An action for actor to rest at a bonfire
 */
public class RestAction extends Action {
    private Bonfire bonfire;
    private Location bonfireLocation;

    /**
     * Constructor.
     *
     * @param bonfire the name of the bonfire
     * @param bonfireLocation the location of the bonfire
     */
    public RestAction(Bonfire bonfire, Location bonfireLocation){
        this.bonfire = bonfire;
        this.bonfireLocation = bonfireLocation;
    }

    /**
     * When an actor rests at Bonfire, reset manager is run to execute RESET feature.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description to show that the world is resetting
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager.getInstance().run();
        bonfire.saveLocation(bonfireLocation);
        return "The world is resetting ...";
    }

    /**
     * Returns a descriptive string about this RestAction.
     *
     * @param actor The actor performing the action.
     * @return the text to put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " rests at " + bonfire;
    }
}
