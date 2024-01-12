package game.actions.weanpon_actions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponAction;
import edu.monash.fit2099.engine.WeaponItem;
import game.enums.Status;
import game.weapons.GameWeaponItem;

/**
 * A weapon action belongs to Darkmoon Longbow.
 * It is activated when the weapon holder becomes aggressive.
 */
public class FireArrowAction extends WeaponAction {
    private GameWeaponItem gameWeaponItem;
    private Actor target;

    /**
     * Constructor.
     *
     * @param weaponItem       the weapon item that has capabilities
     * @param gameWeaponItem   the game weapon item that has capabilities
     * @param target           the attack target
     */
    public FireArrowAction(WeaponItem weaponItem, GameWeaponItem gameWeaponItem, Actor target) {
        super(weaponItem);
        this.gameWeaponItem = gameWeaponItem;
        this.target = target;
    }

    /**
     * Burn the target's ground and hurt anyone who standing on the burning
     * ground by 25 hit points.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description to show that the actor is using Fire Arrow skill to attack target.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        target.addCapability(Status.BURNED);
        result += gameWeaponItem.getAttackAction(target, "").execute(actor, map);
        return "Rarrgh ~\n" + actor + " skin is engulfed with fire! (Ember form)\n" + result;
    }

    /**
     * Return a descriptive string about this Fire Arrow action.
     *
     * @param actor The actor performing the action.
     * @return the text to put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " with fire arrow.";
    }
}
