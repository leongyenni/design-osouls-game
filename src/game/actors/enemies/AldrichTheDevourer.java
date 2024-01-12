package game.actors.enemies;


import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.enums.Status;
import game.weapons.DarkmoonLongbow;

/**
 * A class that represents a boss named Aldrich the Devourer.
 * Aldrich The Devourer is one of the Lord of Cinder.
 */
public class AldrichTheDevourer extends LordOfCinder{

    /**
     * Constructor.
     *
     * @param name the name of Aldrich the Devourer
     */
    public AldrichTheDevourer(String name) {
        super(name, 'A',350);
        this.addItemToInventory(new DarkmoonLongbow(this));
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for Yhorm The Giant
     * @param lastAction The Action Yhorm The Giant took last turn.
     * @param map        the map containing the Yhorm The Giant
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {

        if (this.hitPoints < maxHitPoints / 2) {
            this.addCapability(Status.AGGRESSIVE);
            this.heal((int)(hitPoints*0.2));
        }else{
            this.removeCapability(Status.AGGRESSIVE);
        }

        if(this.hasCapability(Abilities.RANGED_ATTACK)){
            actions.add(this.getInventory().get(0).getAllowableActions());
        }

        return super.playTurn(actions, lastAction, map, display);
    }


}
