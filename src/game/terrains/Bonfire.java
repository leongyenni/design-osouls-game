package game.terrains;

import edu.monash.fit2099.engine.*;
import game.BonfireManager;
import game.actions.bonfire_actions.LightUpAction;
import game.actions.bonfire_actions.RestAction;
import game.actions.bonfire_actions.TeleportAction;
import game.enums.Abilities;
import game.enums.Status;

/**
 * A class that represents a Bonfire.
 */
public class Bonfire extends Ground {
    private String name;
    private BonfireManager bonfireManager;

    /**
     * Constructor.
     *
     * @param name the name of the Bonfire
     * @param bonfireManager a bonfire manager instance
     */
    public Bonfire(String name, BonfireManager bonfireManager) {
        super('B');
        this.name = name;
        this.bonfireManager = bonfireManager;
    }

    /**
     * If the bonfire is LIT and the Actor nearby it has an ability of REST, the Actor can choose to
     * rest at this bonfire;
     * If there are other activated bonfire, the Actor also can choose to move to that bonfire;
     * If the bonfire is not activated, the Actor can choose to light up this bonfire.
     *
     * @param actor     the Actor acting
     * @param location  the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a list of actions if possible; otherwise null
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();

        if (actor.hasCapability(Abilities.REST) && this.hasCapability(Status.LIT)){
            actions.add(new RestAction(this, location));

            for(Location bonfireLocation: bonfireManager.getLocationsList()){
                if(bonfireLocation != location){
                    actions.add(new TeleportAction(bonfireLocation, "to " + bonfireLocation.getGround(), this));
                }
            }

        } else if (actor.hasCapability(Abilities.REST) && !this.hasCapability(Status.LIT)){
            actions.add(new LightUpAction(this,location));
        }

        return actions;
    }

    /**
     * Add the current location of the bonfire to bonfire manager
     *
     * @param currentLocation current location of Bonfire
     */
    public void saveLocation(Location currentLocation) {
        this.bonfireManager.addLocation(currentLocation);
    }

    /**
     * Name of the bonfire.
     *
     * @return the name of the bonfires
     */
    public String toString(){
        return name;
    }


}

