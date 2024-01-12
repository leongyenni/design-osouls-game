package game.actions;

import edu.monash.fit2099.engine.*;

/**
 * An action to collect Token of Souls.
 */
public class CollectTokenAction extends PickUpItemAction {
    /**
     * Constructor.
     *
     * @param item the item to pick up
     */
    public CollectTokenAction(Item item) {
        super(item);
    }

    /**
     * Collect Token of Souls to retrieve lost souls.
     *
     * @see Action#execute(Actor, GameMap)
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a suitable description to display in the UI
     */
     @Override
     public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).removeItem(item);
        item.asSoul().transferSouls(actor.asSoul());
        return actor + " retrieves lost souls.";
     }

     /**
      * Describe the action in a format suitable for displaying in the menu.
      *
      * @see Action#menuDescription(Actor)
      * @param actor The actor performing the action.
      * @return a string, the text to put on the menu
      */
     @Override
     public String menuDescription(Actor actor) {
        return "Retrieves lost souls(" + item + ")";
    }

}
