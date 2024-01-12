package game.weapons;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.WeaponAction;
import edu.monash.fit2099.engine.WeaponItem;
import game.actions.weanpon_actions.EmberFormAction;
import game.actors.enemies.Enemy;
import game.enums.Status;

/**
 * A class that represents a weapon named Yhorm's Great Machete.
 * This weapon is held by Yhorm The Giant.
 */
public class YhormsGreatMachete extends GameWeaponItem{

    /**
     * Constructor.
     *
     * @param holder the actor who holds this weapon
     */
    public YhormsGreatMachete(Actor holder) {
        super("Yhorm's Great Machete",' ',95, "attacks",60, holder);
    }

    /**
     * Yhorm's Great Machete has an active skill called Ember Form.
     * Ember Form is activated when the holder has a status of AGGRESSIVE.
     *
     * @param target the target actor
     * @param direction the direction of target, e.g. "north"
     * @return EmberFormAction if the holder has a status of AGGRESSIVE; otherwise null
     */
    @Override
    public WeaponAction getActiveSkill(Actor target, String direction){
        if(holder.hasCapability(Status.AGGRESSIVE)){
            return new EmberFormAction(this, this, target);
        }
        return null;
    }

    /**
     * Yhorm's Great Machete has an active skill called Ember Form.
     * When this skill is activated (ie the holder becomes aggressive), it increases the
     * success hit rate by 30%.
     *
     * @return a higher hit rate if holder is aggressive; otherwise normal hit rate
     */
    @Override
    public int chanceToHit(){
        if(holder.hasCapability(Status.AGGRESSIVE)){
            return super.chanceToHit() + 30;
        }
        return super.chanceToHit();
    }


}
