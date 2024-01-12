package game.actors;

import edu.monash.fit2099.engine.*;
import game.BonfireManager;
import game.LevelManager;
import game.actions.ResetAction;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.DrinkActor;
import game.interfaces.Level;
import game.interfaces.Resettable;
import game.interfaces.Soul;
import game.items.EstusFlask;
import game.items.TokenOfSouls;
import game.weapons.Broadsword;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Soul, Level, Resettable, DrinkActor {

	private final Menu menu = new Menu();
	private int level;
	private int levelUp;
	private int souls;
	private Location resetLocation;
	private Location previousLocation;
	private final LevelManager levelManager;
	private BonfireManager bonfireManager;


	/**
	 * Constructor.
	 *
	 * @param name          the name of the Actor
	 * @param displayChar   the character that will represent the Actor in the display
	 * @param hitPoints     the Actor's starting hit points
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.levelUp = 5500;
		this.souls = 0;
		this.level = 1;

		this.registerResetInstance();
		this.levelManager = new LevelManager();
		levelManager.appendLevelInstance(this);

		addItems();
		addCapabilities();

	}

	/**
	 * Add items to Player's inventory.
	 */
	private void addItems(){
		this.addItemToInventory(new Broadsword(this));
		EstusFlask estusFlask = new EstusFlask(this);
		this.addItemToInventory(estusFlask);
		levelManager.appendLevelInstance(estusFlask);
	}

	/**
	 * Add capabilities to Player.
	 */
	private void addCapabilities(){
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Abilities.REST);
		this.addCapability(Abilities.FALL);
		this.addCapability(Abilities.GO_TO_NEW_MAP);
	}

	/**
	 * Select and return an action to perform on the current turn.
	 *
	 * @param actions    collection of possible Actions for Player
	 * @param lastAction The Action Player took last turn.
	 * @param map        the map containing the Player
	 * @param display    the I/O object to which messages may be written
	 * @return the Action to be performed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		if(this.hitPoints < this.maxHitPoints/2){
			this.addCapability(Status.DULLNESS);
		}else{
			this.removeCapability(Status.DULLNESS);
		}

		// update resetLocation with currently interacted bonfire location
		resetLocation = bonfireManager.getLocation();

		// Check if the Player is killed
		if(!this.isConscious()){
			map.at(previousLocation.x(),previousLocation.y()).addItem(new TokenOfSouls(this.asSoul()));
			map.moveActor(this,resetLocation); // spawn Player to the top of Bonfire
			return new ResetAction();
		}

		// Check if Player is able to get a level up
		if(this.reachLevel()){
			display.println("LEVEL UP!!\n" + name + " reaches LEVEL " + (level+1) + ".");
			display.println(levelManager.run());
		}

		// Save previous location
		previousLocation = map.locationOf(this);

		display.println(name + "(" + hitPoints + "/" + maxHitPoints + "), Holding " + this.getWeapon() + ", " + souls + " souls, Level " + level);
		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}


	/**
	 * Returns a collection of the Actions that the otherActor can do to the Player.
	 *
	 * @param otherActor the Actor that might be performing attack
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return An empty list of actions
	 */
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		if(otherActor.hasCapability(Status.HOSTILE_TO_PLAYER) && !otherActor.isConscious()){
			otherActor.asSoul().transferSouls(this);
		}
		return new Actions();
	}

	/**
	 * Player will need to be reset every time a reset manager runs.
	 * So, player cannot be removed from the reset list.
	 *
	 * @return true
	 */
	@Override
	public boolean isExist() {
		return true;
	}

	/**
	 * Reset player's hit points to maximum hit points.
	 */
	@Override
	public void resetInstance() {
		this.hitPoints = maxHitPoints;
	}

	/**
	 * Player gets a level up when the souls he retrieved is more than or equal to the base souls,
	 * which is the level up requirement.
	 *
	 * @return true if the Player reach the level up requirement; otherwise false.
	 */
	@Override
	public boolean reachLevel() {
		return souls >= levelUp;
	}

	/**
	 * Upgrade abilities, attributes, and items of the Player.
	 *
	 * @return a description about the upgrade of Player.
	 */
	@Override
	public String levelUp() {
		String result = "";
		this.subtractSouls(levelUp);
		levelUp *= 2;
		level += 1;

		if(level == 2){
			this.increaseMaxHp(150);
			this.addCapability(Abilities.UNLOCK_TWINDAGGER);
			result += "Twin Dagger is unlocked!\n";
		}
		else if (level == 3){
			this.increaseMaxHp(250);
			this.addCapability(Status.HEAVY_ATTACK);
			result += name + "'s weapon is added with HEAVY ATTACK buff.\n";
		}
		result += this + "'s maximum hit points is increased up to " + maxHitPoints + ".\n";
		return result;
	}

	/**
	 * Heal the player with 40% of the maximum hit points.
	 */
	@Override
	public void healHP() {
		this.heal((int)(0.4*maxHitPoints));
	}

	/**
	 * Transfers current number of souls to Token of Souls when player is killed or
	 * fallen from valley.
	 *
	 * @param soulObject the target to transfer the souls to
	 */
	@Override
	public void transferSouls(Soul soulObject) {
		soulObject.addSouls(souls);
		this.subtractSouls(souls);
	}

	/**
	 * Increase souls to player's souls.
	 * Player can receive souls from enemy when he successfully killed the enemy.
	 *
	 * @param souls number of souls to be incremented.
	 * @return true to indicate a successful addition of souls.
	 */
	@Override
	public boolean addSouls(int souls){
		this.souls += souls;
		return true;
	}

	/**
	 * Decrease souls of the player.
	 *
	 * @param souls number of souls to be reduced.
	 * @return true to indicate a successful subtraction of souls.
	 */
	@Override
	public boolean subtractSouls(int souls) {
		this.souls -= souls;
		return true;
	}

	/**
	 * Set the bonfire manager.
	 *
	 * @param bonfireManager an instance af the bonfire manager
	 */
	public void setBonfireManager(BonfireManager bonfireManager){
		this.bonfireManager = bonfireManager;
	}




}

