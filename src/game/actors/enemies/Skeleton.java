package game.actors.enemies;

import game.behaviours.WanderBehaviour;
import game.interfaces.Soul;
import game.weapons.Broadsword;

/**
 * A class that represents a skeleton.
 */
public class Skeleton extends Enemy {

    /**
     * Constructor.
     *
     *@param name  the name of the Skeleton
     */
    public Skeleton(String name) {
        super(name, 's', 100);

        // Skeleton holds Broadsword as weapon
        this.addItemToInventory(new Broadsword(this));

        // Skeleton wanders around
        this.behaviours.add(new WanderBehaviour());
    }

    /**
     * Transfers 250 souls to Player when he is killed by the Player.
     *
     * @param soulObject a target souls
     */
    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(250);
    }

}
