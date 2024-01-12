package game.actions.weanpon_actions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponAction;
import edu.monash.fit2099.engine.WeaponItem;
import game.enums.Status;

/**
 * A weapon action that allows a weapon item to be charged.
 */
public class ChargeAction extends WeaponAction {
    /**
     * Constructor
     *
     * @param weaponItem the weapon item that has capabilities
     */
    public ChargeAction(WeaponItem weaponItem) {
        super(weaponItem);
    }

    /**
     * Perform the Charge action. When an actor is charging the weapon item,
     * he will be disarmed.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description to show that the actor is charging the weapon.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addCapability(Status.DISARMED);
        return actor + " starts charging " + weapon + "(CHARGING).";
    }

    /**
     * Returns a descriptive string about this Charge action
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " charges " + weapon;
    }
}
