package game.terrains;


import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.enums.Status;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {
	private int count = 0;


	/**
	 * Constructor.
	 */
	public Dirt() {
		super('.');
	}

	/**
	 * The dirt is on fire when it has a status of BURNED and this fire will hurt
	 * the Actor standing on it.
	 *
	 * @param location The location of the Ground
	 */
	@Override
	public void tick(Location location) {
		burnActor(location);

		if (this.hasCapability(Status.BURNED)){
			count ++;
			displayChar = 'V';
			if (count > 3){
				this.removeCapability(Status.BURNED);
				count = 0;
			}
		}
		else {
			displayChar = '.';
		}
	}

	private void burnActor(Location location) {
		if (location.containsAnActor() && location.getActor().hasCapability(Status.BURNED)){
			location.getActor().removeCapability(Status.BURNED);
			this.addCapability(Status.BURNED);
			location.getActor().hurt(25);
		}
	}


}
