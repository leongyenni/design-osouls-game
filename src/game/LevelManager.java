package game;

import game.interfaces.Level;


import java.util.ArrayList;
import java.util.List;

/**
 * A manager that executes the LEVEL UP feature of a Level instance.
 */
public class LevelManager {
    private List<Level> levelList;

    /**
     * Constructor.
     */
    public LevelManager(){ levelList=new ArrayList<>();}

    /**
     * Executes the LEVEL UP feature of a Level instance and shows the upgrade
     * of the Level instance.
     *
     * @return a description about the upgrade of Level instance
     */
    public String run() {
        String result = "";

        for(Level levelInstance: levelList){
            result += levelInstance.levelUp();
        }
        return result;
    }

    /**
     * Add the Level instance to the list.
     *
     * @param levelInstance the Level interface instance
     */
    public void appendLevelInstance(Level levelInstance) { levelList.add(levelInstance);}

}
