package game.actions;

import edu.monash.fit2099.engine.*;
import game.actions.weanpon_actions.ChargeAction;
import game.enums.Status;

import java.util.List;

/**
 * An action to swap weapon (new weapon replaces old weapon).
 * It loops through all items in the Actor inventory.
 * The old weapon will be gone.
 */
public class SwapWeaponAction extends PickUpItemAction {

    /**
     * Constructor.
     *
     * @param weapon the new item that will replace the weapon in the Actor's inventory.
     */
    public SwapWeaponAction(Item weapon){
        super(weapon);
    }

    /**
     * Swap current weapon with a new weapon.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description to show the actor is swapping current weapon with a new weapon.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Weapon currentWeapon = actor.getWeapon();
        List<Item> items = actor.getInventory();


        // loop through all inventory
        for(Item item : items){
            if(item.asWeapon() != null){
                actor.removeItemFromInventory(item);
                break; // after it removes that weapon, break the loop.
            }
        }

        // if the ground has item, remove that item.
        // additionally, add new weapon to the inventory (equip).
        super.execute(actor, map);
        return actor + " swaps " + currentWeapon + " with " + item;
    }

}
