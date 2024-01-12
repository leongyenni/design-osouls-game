package game;

import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing Bonfire Manager which store Bonfire locations.
 */
public class BonfireManager {
    private List<Location> locationsList;

    /**
     * Constructor.
     */
    public BonfireManager(){
        locationsList = new ArrayList();
    }

    /**
     * Add the bonfire location instance to the locationList.
     *
     * @param bonfireLocation the location of the bonfire
     */
    public void addLocation(Location bonfireLocation){
       for(int i = 0; i < locationsList.size(); i ++){
            if (bonfireLocation == locationsList.get(i)){
                locationsList.remove(i);
            }
        }
        locationsList.add(bonfireLocation);
    }

    /**
     * An accessor to get the location list of bonfires.
     *
     * @return location list of the bonfires
     */
    public List<Location> getLocationsList() {return locationsList;}

    /**
     * Return the latest interacted bonfire location.
     *
     * @return location of the bonfire
     */
    public Location getLocation(){return locationsList.get(locationsList.size()-1);}


}
