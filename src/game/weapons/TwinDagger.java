package game.weapons;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.PickUpItemAction;
import edu.monash.fit2099.engine.WeaponAction;
import game.actions.SwapWeaponAction;
import game.actions.weanpon_actions.DoubleAttackAction;
import game.enums.Abilities;
import game.enums.Status;


/**
 * A class that represents a weapon named TwinDagger.
 * Only Player can equip this weapon.
 */
public class TwinDagger extends GameWeaponItem{


    /**
     * Constructor.
     *
     * @param holder the actor who holds this weapon
     */
    public TwinDagger(Actor holder) {
        super("Twin Dagger", 'x',85,"slashs",70, holder);
    }


    /**
     * Twin Dagger has a passive skill called Light Attack.
     * When the hit points of holder is below half of his maximum hit points, he
     * will become weak and the damage of this weapon is reduced by half.
     *
     * @return halved damage if the holder becomes weak; otherwise normal damage
     */
    @Override
    public int damage(){
        if(holder.hasCapability(Status.DULLNESS)){
            return super.damage()/2;
        }
        return super.damage();
    }

    /**
     * Twin Dagger has a passive skill called Light Attack.
     * When the hit points of holder is below half of his maximum hit points, he
     * will become weak and the hit rate is decreased by 20%.
     *
     * @return reduced hit rate if the holder becomes weak; otherwise normal hit rate
     */
    public int chanceToHit(){
        if(holder.hasCapability(Status.DULLNESS)){
            return super.chanceToHit() - 20;
        }
        return super.chanceToHit();
    }


    /**
     * Twin Dagger has an active skill called Double Attack which can attacks the target twice
     * in one turn.
     *
     * @param target    the target actor
     * @param direction the direction of target, e.g. "north"
     * @return DoubleAttackAction
     */
    @Override
    public WeaponAction getActiveSkill(Actor target, String direction) {
        return new DoubleAttackAction(this, this, target, direction);
    }

    /**
     * Allow player to swap his weapon with Twin Dagger.
     *
     * @param  actor an actor that will interact with this item
     * @return SwapWeaponAction to allow player to pick up when player reaches Level 2;
     * otherwise null
     */
    public PickUpItemAction getPickUpAction(Actor actor){
        if(actor.hasCapability(Abilities.UNLOCK_TWINDAGGER)){
            return new SwapWeaponAction(this);
        }
        return null;
    }


}
