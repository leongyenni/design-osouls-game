package game;

import edu.monash.fit2099.engine.*;
import game.actors.Player;
import game.actors.enemies.*;
import game.enums.Status;
import game.terrains.*;
import game.weapons.StormRuler;
import game.weapons.TwinDagger;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * The main class for the Jurassic World game.
 *
 */
public class Application {

	public static void main(String[] args) {

		World world = new World(new Display());

		BonfireManager bonfireManager = new BonfireManager();
		FancyGroundFactory groundFactory1 = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Valley(), new Cemetery());

		List<String> firstMap = Arrays.asList(
				"..++++++..+++...........................++++......+++.................+++.......",
				"........+++++..............................+++++++......c..........+++++........",
				"...........+++.......................................................+++++......",
				"............c...........................................................++......",
				".........................................................................+++....",
				"............................+.............................................+++...",
				".............................+++.......++++.....................................",
				".............................++.......+......................++++...............",
				".............................................................+++++++............",
				"..................................###___###...................+++...............",
				"..................................#_______#......................+++............",
				"...........++.....................#_______#.......................+.............",
				".........+++......................#_______#........................++...........",
				"............+++...................####_####..........................+..........",
				"..............+................................................c......++........",
				"..............++.................................................++++++.........",
				"............+++...................................................++++..........",
				"+..................................................................++...........",
				"++...+++................c........................................++++...........",
				"+++......................................+++........................+.++........",
				"++++.......++++.........................++.........................+....++......",
				"#####___#####++++......................+...............................+..+.....",
				"_..._....._._#.++......................+.................c.................+....",
				"...+.__..+...#+++...........................................................+...",
				"...+.....+._.#.+.....+++++...++..............................................++.",
				"___.......___#.++++++++++++++.+++.............................................++");
		GameMap profaneCapital = new GameMap(groundFactory1, firstMap);
		world.addGameMap(profaneCapital);

		Player player = new Player("Unkindled", '@', 200);
		world.addPlayer(player, profaneCapital.at(38, 12));

		// Place Yhorm the Giant/boss in the map
		Enemy yhormTheGiant = new YhormTheGiant("Yhorm the Giant");
		profaneCapital.at(6, 25).addActor(yhormTheGiant);
		yhormTheGiant.storeLocation(profaneCapital.at(6,25));

		// Place Storm Ruler beside Yhorm the Giant
		profaneCapital.at(7,25).addItem(new StormRuler(player));

		// Place 5 Skeletons in the map
		Enemy skeleton1 = new Skeleton("Skeleton");
		profaneCapital.at(20,18).addActor(skeleton1);
		skeleton1.storeLocation(profaneCapital.at(20, 18));

		Enemy skeleton2 = new Skeleton("Skeleton");
		profaneCapital.at(40,8).addActor(skeleton2);
		skeleton2.storeLocation(profaneCapital.at(40, 8));

		Enemy skeleton3 = new Skeleton("Skeleton");
		profaneCapital.at(40,18).addActor(skeleton3);
		skeleton3.storeLocation(profaneCapital.at(40,18));

		Enemy skeleton4 = new Skeleton("Skeleton");
		profaneCapital.at(4,10).addActor(skeleton4);
		skeleton4.storeLocation(profaneCapital.at(4,10));

		Enemy skeleton5 = new Skeleton("Skeleton");
		profaneCapital.at(20,3).addActor(skeleton5);
		skeleton5.storeLocation(profaneCapital.at(20,3));

		// Anor Londo

		FancyGroundFactory groundFactory2 = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Valley(), new Cemetery());

		List<String> secondMap = Arrays.asList(
				"..........+++...........................................+++.......",
				"........+..++.........................................+++++.......",
				"............+++......................++........c..........++......",
				"....c................................+.....................++.....",
				".....................................+........................++..",
				"...................c........................................+++...",
				"....................................c................+++....+.....",
				"...................................................+++............",
				".......c.....................................+++..................",
				"........................................................+++.......",
				"......................######___###################..........c.....",
				"............++........#..........._..#...........#................",
				"......................#...#...#...#..#....._.....#................",
				".......+..............#.....+...............+....#................",
				"......................#...#....#..#.........+....#...+............",
				".....c................#.............__...........#................",
				"......................##__########################................",
				"..........+.............................................++........",
				"..............c...................................................");
		GameMap anorLondo = new GameMap(groundFactory2, secondMap);
		world.addGameMap(anorLondo);

		// Place Twin Dagger in the chamber of second map
		anorLondo.at(23,11).addItem(new TwinDagger(player));

		// Place 6 Skeletons in the map
		Enemy skeleton6 = new Skeleton("Skeleton");
		anorLondo.at(0,18).addActor(skeleton6);
		skeleton6.storeLocation(anorLondo.at(0, 18));

		Enemy skeleton7 = new Skeleton("Skeleton");
		anorLondo.at(45,6).addActor(skeleton7);
		skeleton7.storeLocation(anorLondo.at(0, 6));

		Enemy skeleton8 = new Skeleton("Skeleton");
		anorLondo.at(51,11).addActor(skeleton8);
		skeleton8.storeLocation(anorLondo.at(15,11));

		Enemy skeleton9 = new Skeleton("Skeleton");
		anorLondo.at(4,17).addActor(skeleton9);
		skeleton9.storeLocation(anorLondo.at(4,17));

		Enemy skeleton10 = new Skeleton("Skeleton");
		anorLondo.at(19,3).addActor(skeleton10);
		skeleton10.storeLocation(anorLondo.at(19,3));

		Enemy skeleton11 = new Skeleton("Skeleton");
		anorLondo.at(17,4).addActor(skeleton11);
		skeleton11.storeLocation(anorLondo.at(17,4));

		// Place Aldrich the Devourer/boss in the second map
		Enemy aldrichTheDevourer = new AldrichTheDevourer("Aldrich the Devourer");
		anorLondo.at(36,13).addActor(aldrichTheDevourer);
		aldrichTheDevourer.storeLocation(anorLondo.at(36,13));

		// Place fog door in the first map
		Ground fogDoor1 = new FogDoor(anorLondo.at(34,0), "to Anor Londo!");
		profaneCapital.at(38,25).setGround(fogDoor1);

		// Place fog door in the second map
		Ground fogDoor2 = new FogDoor(profaneCapital.at(38,25), "to Profane Capital!");
		anorLondo.at(34,0).setGround(fogDoor2);

		// Place Firelink Shrine Bonfire in the first map
		Ground firelinkShrineBonfire = new Bonfire("Firelink Shrine Bonfire", bonfireManager);
		firelinkShrineBonfire.addCapability(Status.LIT);
		profaneCapital.at(38,11).setGround(firelinkShrineBonfire);

		// Place Anor Londo's Bonfire in the second map
		Ground anorLondosBonfire = new Bonfire("Anor Londo's Bonfire", bonfireManager);
		anorLondo.at(37, 0).setGround(anorLondosBonfire);

		bonfireManager.addLocation(profaneCapital.at(38,11));
		player.setBonfireManager(bonfireManager);
		world.run();

	}
}
