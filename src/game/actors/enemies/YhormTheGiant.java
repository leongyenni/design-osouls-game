package game.actors.enemies;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Soul;
import game.weapons.YhormsGreatMachete;

/***
 * A class that represents a boss named Yhorm The Giant.
 * Yhorm The Giant is one of the Lord of Cinder.
 */
public class YhormTheGiant extends LordOfCinder{

    /**
     * Constructor.
     *
     * @param name  the name of Yhorm The Giant
     */
    public YhormTheGiant(String name) {
        super(name, 'Y', 500);
        this.addCapability(Abilities.WEAK_TO_STORM_RULER);
        this.addItemToInventory(new YhormsGreatMachete(this));
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

        if(this.hitPoints < maxHitPoints/2){
            this.addCapability(Status.AGGRESSIVE);
        }

        if (this.hasCapability(Status.STUN) && this.isConscious()){
            this.removeCapability(Status.STUN);
            return new DoNothingAction();
        }

        return super.playTurn(actions,lastAction,map,display);
    }






}
