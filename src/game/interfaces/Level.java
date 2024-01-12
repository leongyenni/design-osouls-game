package game.interfaces;

/**
 * Interface for Level.
 *
 */
public interface Level {
    /**
     * Allows any classes that use this interface to upgrade abilities, attributes, and items.
     *
     * @return a string describing the upgrade of Actor, Item, or Weapon.
     */
    String levelUp();


    /**
     * Check if the Level instance reach the requirement of getting a level up.
     *
     * @return true if the Level instance reach the level up requirement; otherwise false.
     */
    boolean reachLevel();


}
