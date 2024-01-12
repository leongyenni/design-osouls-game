package game.actions.weanpon_actions;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.enums.Status;
import game.weapons.GameWeaponItem;

/**
 * A weapon action that belongs to Storm Ruler.
 * It is only available when the Storm Ruler is fully charged, and it can be used to
 * attack Yhorm The Giant only.
 */
public class WindSlashAction extends WeaponAction {
    private Actor target;
    private GameWeaponItem gameWeaponItem;

    /**
     * Constructor.
     *
     * @param weaponItem       the weapon item that has capabilities
     * @param gameWeaponItem   the game weapon item that has capabilities
     * @param target           the attack target
     */
    public WindSlashAction(WeaponItem weaponItem, GameWeaponItem gameWeaponItem, Actor target) {
        super(weaponItem);
        this.gameWeaponItem = gameWeaponItem;
        this.target = target;
    }

    /**
     * Perform the Wind Slash action. The weapon item deals 2x damage to target with 100%
     * hit rate. The stunned target cannot move for one turn.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description to show that the actor is stunning the target.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        target.addCapability(Status.STUN);
        weapon.addCapability(Status.STUN);
        weapon.removeCapability(Status.FULLY_CHARGED);

        gameWeaponItem.getAttackAction(target, "").execute(actor, map);
        return menuDescription(actor) + " with Wind Slash.";
    }

    /**
     * Return a descriptive string about this Wind Slash action.
     *
     * @param actor The actor performing the action.
     * @return the text to put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " stuns " + target;
    }



}
