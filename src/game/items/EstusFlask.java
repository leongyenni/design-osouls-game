package game.items;

import edu.monash.fit2099.engine.Item;
import game.actions.DrinkAction;
import game.interfaces.DrinkActor;
import game.interfaces.DrinkItem;
import game.interfaces.Level;
import game.interfaces.Resettable;

/**
 * A class that represents a health portion called Estus Flask.
 *
 */
public class EstusFlask extends Item implements Resettable, DrinkItem, Level {
    private int numOfCharge;
    private int maxNumOfCharge;
    private int level;
    private DrinkActor holder;

    /***
     * Constructor.
     *
     * @param holder Actor who holds this item
     */
    public EstusFlask(DrinkActor holder) {
        super("Estus Flask", ' ', false);
        this.level = 1;
        this.numOfCharge = 3;
        this.maxNumOfCharge = numOfCharge;
        this.holder = holder;
        this.allowableActions.add(new DrinkAction(this));
        this.registerResetInstance();
    }

    /**
     * When it is consumed, the number of charge is decreased by 1 and it will heal
     * holder's HP.
     */
    @Override
    public void consume(){
        numOfCharge -= 1;
        holder.healHP();
    }

    /**
     * Accessor for the number of charge left.
     *
     * @return number of charge
     */
    @Override
    public int amount() {
        return numOfCharge;
    }

    /**
     * Accessor for the maximum number of charge.
     *
     * @return maximum number of charge
     */
    @Override
    public int maxAmount() {return maxNumOfCharge;}

    /**
     * Number of charge of Estus Flask is refilled to maximum charges.
     */
    @Override
    public void resetInstance() { numOfCharge = maxNumOfCharge; }

    /**
     * Estus Flask will need to be reset every time a reset manager runs.
     * So, it cannot be removed from the reset list.
     *
     * @return true
     */
    @Override
    public boolean isExist() {
        return true;
    }

    /**
     * Upgrade the number of charges up to 5 when the holder reaches Level 2.
     * Estus flask is refilled to maximum charges when the holder is level up.
     *
     * @return a string describing the upgrade of Actor, Item, or Weapon.
     */
    @Override
    public String levelUp() {
        String result = "";
        if(level == 1){
            this.maxNumOfCharge = 5;
            result += this + " is upgraded up to 5 charges.";
        }
        level ++;
        numOfCharge = maxNumOfCharge;
        result += this + " is refilled to full charges.\n";
        return result;
    }


    /**
     * Estus Flask gets a level up when the holder is level up.
     *
     * @return true.
     */
    @Override
    public boolean reachLevel() { return true;}



}
