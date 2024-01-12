package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Behaviour;

/**
 * A class that decides which action to perform to attack the target.
 * This class is only used by enemy.
 */
public class AttackBehaviour implements Behaviour {


    /**
     * Return an Action (Attack Action or other Weapon Action) to attack the target if possible.
     *
     * @param actor     the Actor acting
     * @param map       the GameMap containing the Actor
     * @return an Action, or null if no Action is possible
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);

        if (!map.contains(actor)){
            return null;
        }


        for (Exit exit1 : here.getExits()) {
            Location destination = exit1.getDestination();
            Actor target = destination.getActor();

            if (destination.containsAnActor() && target.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                if (actor.getWeapon().getActiveSkill(target, exit1.getName()) != null) {
                    return actor.getWeapon().getActiveSkill(target, exit1.getName());
                }
                else{
                    return new AttackAction(target,exit1.getName());
                }
            }
        }

        return null;
}


}
