package game.actions.weanpon_actions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponAction;
import edu.monash.fit2099.engine.WeaponItem;
import game.weapons.GameWeaponItem;

/**
 * A weapon action belongs to Twin Dagger.
 *
 */
public class DoubleAttackAction extends WeaponAction {
    private GameWeaponItem gameWeaponItem;
    private Actor target;
    private String direction;

    /**
     * Constructor.
     *
     * @param weaponItem       the weapon item that has capabilities
     * @param gameWeaponItem   the game weapon item that has capabilities
     * @param target           the attack target
     * @param direction        the direction of attack
     */
    public DoubleAttackAction(WeaponItem weaponItem, GameWeaponItem gameWeaponItem, Actor target, String direction) {
        super(weaponItem);
        this.gameWeaponItem = gameWeaponItem;
        this.target = target;
        this.direction = direction;
    }

    /**
     * Attack the target twice in one turn.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description to show that the actor is using this active skill.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        result += gameWeaponItem.getAttackAction(target,direction).execute(actor, map);
        result += gameWeaponItem.getAttackAction(target,direction).execute(actor, map);
        return result;
    }

    /**
     * Return a descriptive string about this Double Attack action.
     *
     * @param actor The actor performing the action.
     * @return the text to put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " slashes " + target + " with double attack.";
    }
}
