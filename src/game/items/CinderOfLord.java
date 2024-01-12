package game.items;

import edu.monash.fit2099.engine.Item;


/**
 * A class that represents Cinder Of lord which is dropped when Lord Of Cinder is killed.
 */
public class CinderOfLord extends Item {
    /**
     * Constructor.
     *
     * @param name Name of Lord Of Cinder
     */
    public CinderOfLord(String name) {
        super("Cinder of " + name, '%', true);
    }
}
