package game.actions.bonfire_actions;

import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.terrains.Bonfire;

/**
 * An action to light up Bonfire.
 */

public class LightUpAction extends Action {
    private Bonfire bonfire;
    private Location bonfireLocation;

    /**
     * Constructor.
     *
     * @param bonfire the name of the bonfire
     * @param bonfireLocation the location of the bonfire
     */
    public LightUpAction(Bonfire bonfire, Location bonfireLocation){
        this.bonfire = bonfire;
        this.bonfireLocation = bonfireLocation;
    }

    /**
     * Light up the bonfire to activate it. Actor can only rest at the bonfire or teleport to
     * other bonfires only when the bonfire is lit.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description to show that bonfire is lit
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        bonfire.addCapability(Status.LIT);
        bonfire.saveLocation(bonfireLocation);
        return bonfire + " lit.";
    }

    /**
     * Returns a descriptive string about this LightUpAction.
     *
     * @param actor The actor performing the action.
     * @return the text to put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " lights the " + bonfire;
    }
}
