package game.weapons;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.enums.Status;

/**
 * A class that represents a weapon item that can be used in this game.
 */
public class GameWeaponItem extends WeaponItem {
    protected Actor holder;
    protected Location here;

    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target
     * @param holder      the actor who holds this game weapon item
     */
    public GameWeaponItem(String name, char displayChar, int damage, String verb, int hitRate, Actor holder) {
        super(name, displayChar, damage, verb, hitRate);
        this.portable = false;
        this.holder = holder;
    }


    /**
     * Create and return an Attack Action to attack the target.
     *
     * @param target        the Actor to attack
     * @param direction     the direction of the attack
     * @return a new Attack Action
     */
    public Action getAttackAction(Actor target, String direction) {return new AttackAction(target, direction);
    }

    /**
     * If the holder has a status of HEAVY ATTACK, hit rate of holder's weapon
     * is increased by 25%.
     *
     * @return a higher hit rate if holder has a status of HEAVY ATTACK; otherwise
     *         normal hit rate.
     */
    @Override
    public int chanceToHit() {
        if (holder.hasCapability(Status.HEAVY_ATTACK)){
            return hitRate + 25;
        }
        return hitRate;
    }

    /**
     * If the holder has a status of HEAVY ATTACK, damage of holder's weapon
     * is doubled.
     *
     * @return double damage if the holder has a status of HEAVY ATTACK; otherwise
     *         normal damage.
     */
    public int damage(){
        if (holder.hasCapability(Status.HEAVY_ATTACK)){
            return damage * 2;
        }
        return damage;
    }


    /**
     * Get the location of weapon and check if an Actor is holding it.
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    public void tick(Location currentLocation, Actor actor) {
        this.here = currentLocation;

        for(Item item: actor.getInventory()){
            if(item == this){
                this.addCapability(Status.HOLD);
                break;
            } else{
                this.removeCapability(Status.HOLD);
            }
        }
    }

}
