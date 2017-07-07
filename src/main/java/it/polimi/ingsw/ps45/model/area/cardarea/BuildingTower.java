package it.polimi.ingsw.ps45.model.area.cardarea;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.polimi.ingsw.ps45.gson.PropertyBasedInterfaceMarshal;
import it.polimi.ingsw.ps45.model.area.HasDictionary;
import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * Class that holds and manages all BuildingCardAreas of the game.
 */
public class BuildingTower implements Tower, HasDictionary{
	private static final Logger LOGGER = Logger.getLogger( BuildingTower.class.getName());
	private HashMap<String, BuildingCardArea> dictionary;
	private BuildingCardArea b0;
	private BuildingCardArea b1;
	private BuildingCardArea b2;
	private BuildingCardArea b3;
	
	/**
 	 * Constructor
 	 * Reads all the areas from file.
	 */
	public BuildingTower(){
		dictionary = new HashMap<String, BuildingCardArea>();
		b0 = loadFromFile("serialized/areas/cardareas/BuildingCardAreaGroundFloor.json");
		b1 = loadFromFile("serialized/areas/cardareas/BuildingCardAreaFirstFloor.json");
		b2 = loadFromFile("serialized/areas/cardareas/BuildingCardAreaSecondFloor.json");
		b3 = loadFromFile("serialized/areas/cardareas/BuildingCardAreaThirdFloor.json");
		
		dictionary.put("b0", b0);
		dictionary.put("b1", b1);
		dictionary.put("b2", b2);
		dictionary.put("b3", b3);
	}
	

	/**
	 * @return true if any of the areas in the tower is occupied.
	 */
	@Override
	public boolean isOccupied() {
		int occupants = b0.getOccupants().size() + b1.getOccupants().size() + b2.getOccupants().size() + b3.getOccupants().size(); 
		if(occupants > 0)return true;
		return false;
	}

	/**
	 * @param p the player that the area will be checked if is occupied by.
	 * @return true if any of the areas in the tower is occupied by the player in the parameters.
	 */
	@Override
	public boolean isOccupiedByPlayer(Player p) {
		return 	    b0.isOccupiedByPlayerWithColoredPawn(p) || 
					b1.isOccupiedByPlayerWithColoredPawn(p) || 
					b2.isOccupiedByPlayerWithColoredPawn(p) || 
					b3.isOccupiedByPlayerWithColoredPawn(p);
	}
	
	/**
	 * Loads a BuildingCardAreas from file.
	 * @param path the path of the serialized json area file.
	 */
	public BuildingCardArea loadFromFile(String path){
		Gson gson = new GsonBuilder()
                .registerTypeAdapter(Effect.class,
                        new PropertyBasedInterfaceMarshal()).create();
	 
		BuildingCardArea c = null;
		try {
			c = gson.fromJson(new FileReader(path), BuildingCardArea.class);
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, "context", e);
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, "context", e);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, "context", e);
		}
	 return c;
	}
	
	/**
	 * @return the BuildingCardArea on the ground floor of the tower.
	 */
	public BuildingCardArea getGroundFloor() {
		return b0;
	}

	/**
	 * @return the BuildingCardArea on the first floor of the tower.
	 */
	public BuildingCardArea getFirstFloor() {
		return b1;
	}

	/**
	 * @return the BuildingCardArea on the second floor of the tower.
	 */
	public BuildingCardArea getSecondFloor() {
		return b2;
	}

	/**
	 * @return the BuildingCardArea on the third floor of the tower.
	 */
	public BuildingCardArea getThirdFloor() {
		return b3;
	}
	
	/**
	 * @return an ArrayList containing all the ares in the tower.
	 */
	public ArrayList<BuildingCardArea> getAreas(){
		ArrayList<BuildingCardArea> list = new ArrayList<BuildingCardArea>();
		list.add(b0);
		list.add(b1);
		list.add(b2);
		list.add(b3);
		return list;
	}
	
	/**
	 * @throws Exception if the an area with the given name doesn't exist
	 * @param s name of the area. Also key in the dictionary.
	 * @return A BuildingCardArea that corresponds with the parameter string s.
	 */
	public BuildingCardArea getAreaFromString(String s) throws Exception {
		if(!dictionary.containsKey(s.toLowerCase())) throw new Exception("No such key");
		return dictionary.get(s.toLowerCase());
	}

	/**
	 * @return The a HashMap which stores the name of an area as key and it's instantiated object as value.
	 */
	public HashMap<String, BuildingCardArea> getDictionary() {
		return dictionary;
	}
	
}


