package game.weapons;

import edu.monash.fit2099.engine.*;
import game.actions.SwapWeaponAction;
import game.actions.weanpon_actions.ChargeAction;
import game.actions.weanpon_actions.EmberFormAction;
import game.actions.weanpon_actions.WindSlashAction;
import game.enums.Abilities;
import game.enums.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class that represents a weapon named Storm Ruler.
 * Only Player can equip this weapon.
 */
public class StormRuler extends GameWeaponItem {
    private Random rand = new Random();
    private int count;

    /**
     * Constructor.
     *
     * @param holder the actor who holds this weapon
     */
    public StormRuler(Actor holder) {
        super("Storm Ruler", '7', 70, "attacks", 60, holder);
    }

    /**
     * Allow player to swap his weapon with Storm Ruler.
     *
     * @param actor an actor that will interact with this item
     * @return SwapWeaponAction to allow player to pick up; otherwise null because only
     * player can equip this weapon.
     */
    public PickUpItemAction getPickUpAction(Actor actor) {
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            return new SwapWeaponAction(this);
        }
        return null;
    }

    /**
     * Accessor for damage done by this weapon.
     *
     * Storm Ruler has 2 passive skills (Critical Strike and Dullness) and an active
     * skill (Wind Slash).
     *
     * When Critical Strike or Wind Slash is activated, Storm Ruler would deal 2x damage;
     * When Storm Ruler is used to attack the enemies that are not weak to it, Dullness
     * is activated, the damage is reduced by halved.
     *
     * @return halved damage if Storm Ruler is used to attack enemies that are not weak to it,
     * or double damage if Storm Ruler success to use Critical Strike or WindSlash;
     * otherwise normal damage
     */
    @Override
    public int damage() {

        if (this.hasCapability(Status.DULLNESS)) {
            return super.damage() / 2;
        }

        if (this.hasCapability(Status.STUN) || rand.nextInt(100) <= 20) {
            this.removeCapability(Status.STUN);
            return super.damage() * 2;
        }

        return super.damage();
    }

    /**
     * Accessor for hit rate done by this weapon.
     * Storm Ruler has an active skill called Wind Slash.
     * When this active skill is activated, hit rate of Storm Ruler is increased to 100%.
     *
     * @return 100% hit rate if Storm Ruler success to use one of these weapon skill;
     * otherwise normal hit rate
     */
    @Override
    public int chanceToHit() {
        if (this.hasCapability(Status.STUN)) {
            return 100;
        }
        return super.chanceToHit();
    }

    /**
     * Storm Ruler has an active skill called Wind Slash.
     * Wind Slash is activated when Storm Ruler is fully charged.
     *
     * @param target    the target actor
     * @param direction the direction of target, e.g. "north"
     * @return WindSlashAction if StormRuler is fully charged; otherwise null
     */
    @Override
    public WeaponAction getActiveSkill(Actor target, String direction) {
        if (!target.hasCapability(Abilities.WEAK_TO_STORM_RULER)) {
            this.addCapability(Status.DULLNESS);
        } else {
            this.removeCapability(Status.DULLNESS);
            if (this.hasCapability(Status.FULLY_CHARGED)) {
                return new WindSlashAction(this, this, target);
            }
        }
        return null;
    }

    /**
     * An Actor can charge Storm Ruler whenever he is holding it if Storm Ruler is
     * not being charged or has been fully charged.
     *
     * @return a list of allowable actions that an Actor can execute
     */
    @Override
    public List<Action> getAllowableActions() {
        allowableActions = new Actions();

        // not charging or fully charged
        if (this.hasCapability(Status.HOLD) && count == 0 && !this.hasCapability(Status.FULLY_CHARGED)) {
            allowableActions.add(new ChargeAction(this));
        }
        return allowableActions.getUnmodifiableActionList();
    }

    /**
     * Display the name of Storm Ruler and to show the status of Storm Ruler if
     * it is being charged, or it has been fully charged.
     *
     * @return the name and status(if any) of Storm Ruler.
     */
    @Override
    public String toString() {

        if (this.hasCapability(Status.FULLY_CHARGED)) {
            return super.toString() + "(FULLY CHARGED)";
        } else if (count != 0) {
            return super.toString() + "(CHARGING)";
        }
        return super.toString();
    }

    /**
     * Check how many turns the Storm Ruler is being charged.
     * Storm Ruler has to be charged for 3 turns in order to be fully charged.
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation,actor);

        if (actor.hasCapability(Status.DISARMED)){
            count ++;
            if(count > 3){
                count = 0;
                actor.removeCapability(Status.DISARMED);
                this.addCapability(Status.FULLY_CHARGED);
            }
        }
    }


}