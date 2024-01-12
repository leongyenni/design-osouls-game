package game.interfaces;

/**
 * Interface for drink items.
 * It provides method needed by the item.
 *
 */
public interface DrinkItem {
    /**
     * Charge of a Drink Item is decreased when an Actor consumes it.
     */
    void consume();

    /**
     * Accessor to get the number of charge of a Drink Item
     *
     * @return amount of a Drink Item
     */
    int amount();

    /**
     * Accessor to get the maximum number of charge of a Drink Item
     *
     * @return maximum amount of a Drink Item
     */
    int maxAmount();
}
