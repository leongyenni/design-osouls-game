package game.terrains;


import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.actors.enemies.Undead;

import java.util.Random;

/**
 * A class that represents a cemetery.
 */
public class Cemetery extends Ground {
    private Random rand = new Random();

    /**
     * Constructor.
     */
    public Cemetery() {
        super('c');
    }

    /**
     * Cemetery has 25% to spawn an Undead.
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (rand.nextInt(100) <= 25 && !location.containsAnActor()) {
            location.addActor(new Undead("Undead"));
        }
    }

}
