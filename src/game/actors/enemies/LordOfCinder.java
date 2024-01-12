package game.actors.enemies;


import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Soul;
import game.items.CinderOfLord;

/**
 * The boss of Design o' Souls
 */
public abstract class LordOfCinder extends Enemy {

    /**
     * Constructor.
     *
     * @param name        the name of the Lord of Cinder
     * @param displayChar the character that will represent the Lord of Cinder in the display
     * @param hitPoints   the Lord of Cinder's starting hit points
     */
    public LordOfCinder(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    /**
     * Transfers 5000 souls to Player when he is killed by the Player.
     *
     * @param soulObject a target souls
     */
    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(5000);
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Enemy
     * @param lastAction The Action this Enemy took last turn.
     * @param map        the map containing the Enemy
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if(!this.isConscious()){
            map.at(map.locationOf(this).x(),map.locationOf(this).y()).addItem(new CinderOfLord(name));
        }
        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * Reset Lord of Cinder's position, health and skills.
     */
    @Override
    public void resetInstance() {
        super.resetInstance();
        this.removeCapability(Status.AGGRESSIVE);
    }


}
