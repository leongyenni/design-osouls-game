package game.items;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.PickUpItemAction;
import game.actions.CollectTokenAction;
import game.enums.Status;
import game.interfaces.Soul;

/**
 * A class that represents Token of Souls which can be collected to retrieve the lost souls.
 */
public class TokenOfSouls extends Item implements Soul {
    private int souls;
    private Soul collector;

    /**
     * Constructor.
     *
     * @param collector to collect souls
     */
    public TokenOfSouls(Soul collector) {
        super("Token of souls", '$',true);
        this.collector = collector;
        collector.transferSouls(this);
    }


    /**
     * Only player can collect the Token of Souls to retrieves his lost souls.
     *
     * @param actor an actor that will interact with this item
     * @return CollectTokenAction to allow player to pick up the token; otherwise null because only
     *         player can collect the token.
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            return new CollectTokenAction(this);
        }
        return null;
    }

    /**
     * Transfers current number of souls to a soul object.
     *
     * @param soulObject a target soul.
     */
    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(souls);
        this.subtractSouls(souls);
    }

    /**
     * Receives souls from a soul object.
     *
     * @param souls number of souls to be incremented.
     * @return true to indicate a successful addition of souls.
     */
    @Override
    public boolean addSouls(int souls) {
        this.souls += souls;
        return true;
    }

    /**
     * Decrease the souls of the token of souls.
     *
     * @param souls number of souls to be reduced.
     * @return true to indicate a successful subtraction of souls.
     */
    @Override
    public boolean subtractSouls(int souls) {
        this.souls -= souls;
        return true;
    }

    /**
     * Return the number of souls.
     *
     * @return a description for the number of souls
     */
    public String toString(){
        return souls + " souls";
    }

}
