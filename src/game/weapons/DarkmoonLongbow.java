package game.weapons;

import edu.monash.fit2099.engine.*;
import game.actions.weanpon_actions.FireArrowAction;
import game.enums.Abilities;
import game.enums.Status;

import java.util.List;
import java.util.Random;


/**
 * A class that represents a weapon named Darkmoon Longbow.
 */
public class DarkmoonLongbow extends GameWeaponItem{
    private Random rand = new Random();

    /**
     * Constructor.
     *
     * @param holder the actor who holds this weapon
     */
    public DarkmoonLongbow(Actor holder) {
        super("Darkmoon Longbow", ' ',70,"attacks",80, holder);
    }

    /**
     * Accessor for damage done by this weapon.
     * Darkmoon Longbow has a passive skill called Critical Hit.
     * It has a 15% success rate to deal double damage with a normal attack.
     *
     * @return double damage if Darkmoon Longbow success to use this weapon skill;
     *         otherwise normal damage
     */
    @Override
    public int damage(){
        if(rand.nextInt(100) <= 15){
            return super.damage() * 2;
        }
        return super.damage();
    }

    /**
     * Accessor for hit rate done by this weapon.
     * Darkmoon Longbow automatically misses attack when target is blocked by wall
     *
     * @return 0% hit rate if target is blocked by wall; otherwise normal hit rate
     */
    public int chanceToHit(){
        if (holder.hasCapability(Status.MISS_ATTACK)){
            holder.removeCapability(Status.MISS_ATTACK);
            return 0;
        }
        return super.chanceToHit();
    }

    /**
     * Darkmoon Longbow has an active skill called Fire Arrow.
     * Fire Arrow is activated when the holder has a status of AGGRESSIVE.
     *
     * @param target    the target actor
     * @param direction the direction of target, e.g. "north"
     * @return FireArrowAction if the holder has a status of AGGRESSIVE; otherwise null
     */
    @Override
    public WeaponAction getActiveSkill(Actor target, String direction) {
        if (holder.hasCapability(Status.AGGRESSIVE)){
            return new FireArrowAction(this,this,target);
        }
        return null;
    }

    /**
     * DarkmoonLongbow allows the holder to attack his target from 3 squares away. However,
     * the attack will miss the target if target is behind the wall.
     *
     * @return a list of allowable actions (Attack Action and Weapon Action if possible) that an Actor
     *         can execute
     */
    public List<Action> getAllowableActions(){
        allowableActions = new Actions();

        if(this.hasCapability(Status.HOLD)){
            for (Exit exit1 : here.getExits()) {
                for (Exit exit2 : exit1.getDestination().getExits()) {
                    for (Exit exit3 : exit2.getDestination().getExits()) {
                        Location there = exit3.getDestination();

                        if (targetAt(there)) {
                            checkWall(here, there, holder);
                            holder.addCapability(Abilities.RANGED_ATTACK);

                            if (holder.getWeapon().getActiveSkill(there.getActor(), "3 squares away") != null ) {
                                allowableActions.add(this.getActiveSkill( there.getActor(), "3 squares away"));
                            }
                            allowableActions.add(this.getAttackAction(there.getActor(), "3 square away"));
                        }
                    }
                }
            }
        }

        return allowableActions.getUnmodifiableActionList();
    }

    /**
     * Enemy can only attack the Player and vice versa. This method check if the target is within the
     * attack range of this weapon.
     *
     * @param there location of target
     * @return true if target is within the attack range; otherwise false
     */
    private Boolean targetAt(Location there){
        Boolean actorCheck;
        if(holder.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actorCheck = there.containsAnActor() && !there.getActor().hasCapability(Status.HOSTILE_TO_ENEMY);
        }
        else{
            actorCheck = there.containsAnActor() && there.getActor().hasCapability(Status.HOSTILE_TO_ENEMY);
        }
        return actorCheck;
    }

    /**
     * Check if the target is hiding behind the wall.
     *
     * @param here     location of actor
     * @param there    location of target
     * @param actor    the actor who has ranged attack behaviour
     */
    private void checkWall(Location here, Location there, Actor actor) {

        NumberRange xs, ys;

        xs = new NumberRange(Math.min(here.x(), there.x()), Math.abs(here.x() - there.x()) + 1);
        ys = new NumberRange(Math.min(here.y(), there.y()), Math.abs(here.y() - there.y()) + 1);

        for (int x : xs) {
            for (int y : ys) {
                if (there.map().at(x, y).getGround().blocksThrownObjects()) {
                    actor.addCapability(Status.MISS_ATTACK);
                }
            }
        }
    }

    /**
     * Display the name of Darkmoon Longbow and to show that the active skill of
     * Darkmoon Longbow is activated.
     *
     * @return the name and active skill (if any) of Storm Ruler.
     */
    public String toString(){
        if (holder.hasCapability(Status.AGGRESSIVE)){
            return name + "(FIRE ARROW)";
        }
        else{
            return name;
        }
    }



}
