package game.actions.weanpon_actions;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.weapons.GameWeaponItem;

/**
 * A weapon action that belongs to Yhorm's Great Machete.
 * It is activated when the weapon holder becomes aggressive.
 */
public class EmberFormAction extends WeaponAction{
    private Actor target;
    private GameWeaponItem gameWeaponItem;

    /**
     * Constructor.
     *
     * @param weaponItem       the weapon item that has capabilities
     * @param gameWeaponItem   the game weapon item that has capabilities
     * @param target           the attack target
     */
    public EmberFormAction(WeaponItem weaponItem, GameWeaponItem gameWeaponItem, Actor target) {
        super(weaponItem);
        this.gameWeaponItem = gameWeaponItem;
        this.target = target;

    }

    /**
     * Perform the Ember Form action. The holder's success hit rate will be
     * increased by 30%.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description to show that the actor is in Ember form.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        result += gameWeaponItem.getAttackAction(target, "").execute(actor, map);
        return "Rarrgh ~\n" + actor + " skin is engulfed with fire! (Ember form)\n" + result;
    }

    /**
     * Return a descriptive string about this Ember Form action.
     *
     * @param actor The actor performing the action.
     * @return the text to put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " burns surroundings.";
    }

}
