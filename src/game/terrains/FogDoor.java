package game.terrains;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;

/**
 * A class representing a Fog Door
 */
public class FogDoor extends Ground {
    private Location destination;
    private String direction;

    /**
     * Constructor.
     *
     * @param destination the location this fogdoor brings to
     * @param direction   the direction where the fogdoor brings to
     */
    public FogDoor(Location destination, String direction) {
        super('=');
        this.destination = destination;
        this.direction = direction;
    }

    /**
     * Allows only player to interact with it.
     *
     * @param actor the Actor to check
     * @return false if actor does not have GO_TO_NEW_MAP abilities
     */
    @Override
    public boolean canActorEnter(Actor actor) {return actor.hasCapability(Abilities.GO_TO_NEW_MAP);}

    /**
     * Return MoveActorAction if player steps on the fog door.
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a MoveActorAction to the second map
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();

        if(location.containsAnActor()){
             actions.add(new MoveActorAction(destination, this.direction));
        }
        return actions;
    }
}
