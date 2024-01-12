package game.weapons;

import edu.monash.fit2099.engine.Actor;
import game.enums.Abilities;

import java.util.Random;

/**
 *A class that represents a weapon named Broadsword.
 */
public class Broadsword extends GameWeaponItem {
    private Random rand = new Random();

    /**
     * Constructor.
     *
     * @param holder the actor who holds this weapon
     */
    public Broadsword(Actor holder) {
        super("Broadsword", ' ', 30, "slashs", 80, holder);
    }


    /**
     * Accessor for damage done by this weapon.
     * Broadsword has a passive skill called Critical Strike.
     * It has a 20% success rate to deal double damage with a normal attack.
     *
     * @return double damage if Broadsword success to use this weapon skill; otherwise
     *         normal damage
     */
    @Override
    public int damage(){
        if(rand.nextInt(100) <= 20){
            return super.damage() * 2;
        }
        return super.damage();
    }


}
