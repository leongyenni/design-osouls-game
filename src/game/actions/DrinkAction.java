package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.interfaces.DrinkActor;
import game.interfaces.DrinkItem;

/**
 * An action to drink an item.
 */
public class DrinkAction extends Action {
    private DrinkItem drinkItem;


    /**
     * Constructor.
     *
     * @param drinkItem  the name of drink item
     */
    public DrinkAction(DrinkItem drinkItem){
        this.drinkItem = drinkItem;
    }

    /**
     * When an Actor consumes a DrinkItem, the DrinkItem can do something to the Actor
     * and the DrinkItem itself can have some changes.
     * For example, EstusFlask can heal the hit points of Player by 40% when Player
     * drinks it and the number of charges of EstusFlask is reduced by 1.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return  a description to show if the actor is drinking or there is no more
     *          drink item for actor.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        if(drinkItem.amount() > 0){
            drinkItem.consume();
            return actor + " is drinking " + drinkItem + ".";
        }
        else {
            return "There is no more " + drinkItem + ".";
        }
    }


    /**
     * Returns a descriptive string about this drink action.
     *
     * @param actor The actor performing the action.
     * @return the text to put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " drinks " + drinkItem + "(" + drinkItem.amount() + "/" + drinkItem.maxAmount() + ")";
    }

}

