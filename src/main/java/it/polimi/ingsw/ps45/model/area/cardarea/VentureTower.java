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
 * Class that holds and manages all VentureCardAreas of the game.
 */
public class VentureTower implements Tower, HasDictionary{
	private static final Logger LOGGER = Logger.getLogger( VentureTower.class.getName());
	private HashMap<String, VentureCardArea> dictionary;
	private VentureCardArea v0;
	private VentureCardArea v1;
	private VentureCardArea v2;
	private VentureCardArea v3;
	
	/**
 	 * Constructor
 	 * Reads all the areas from file.
	 */
	public VentureTower(){
		dictionary = new HashMap<String, VentureCardArea>();
		
		
		v0 = loadFromFile("serialized//areas//cardareas//VentureCardAreaGroundFloor.json");
		v1 = loadFromFile("serialized//areas//cardareas//VentureCardAreaFirstFloor.json");
		v2 = loadFromFile("serialized//areas//cardareas//VentureCardAreaSecondFloor.json");
		v3 = loadFromFile("serialized//areas//cardareas//VentureCardAreaThirdFloor.json");
		
		
		dictionary.put("v0", v0);
		dictionary.put("v1", v1);
		dictionary.put("v2", v2);
		dictionary.put("v3", v3);
	}
	

	/**
	 * @return true if any of the areas in the tower is occupied.
	 */
	@Override
	public boolean isOccupied() {
		int occupants = v0.getOccupants().size() + v1.getOccupants().size() + v2.getOccupants().size() + v3.getOccupants().size(); 
		if(occupants > 0)return true;
		return false;
	}

	/**
	 * @param p the player that the area will be checked if is occupied by.
	 * @return true if any of the areas in the tower is occupied by the player in the parameters.
	 */
	@Override
	public boolean isOccupiedByPlayer(Player p) {
		return 	    v0.isOccupiedByPlayerWithColoredPawn(p) || 
					v1.isOccupiedByPlayerWithColoredPawn(p) || 
					v2.isOccupiedByPlayerWithColoredPawn(p) || 
					v3.isOccupiedByPlayerWithColoredPawn(p);
	}
	
	/**
	 * Loads a VentureCardAreas from file.
	 * @param path the path of the serialized json area file.
	 */
	public VentureCardArea loadFromFile(String path){
		Gson gson = new GsonBuilder()
                .registerTypeAdapter(Effect.class,
                        new PropertyBasedInterfaceMarshal()).create();
	 
		VentureCardArea c = null;
		try {
			c = gson.fromJson(new FileReader(path), VentureCardArea.class);
		} catch (JsonSyntaxException e) {
			
			LOGGER.log(Level.SEVERE, "context", e);
		} catch (JsonIOException e) {
			
			LOGGER.log(Level.SEVERE, "context", e);
		} catch (FileNotFoundException e) {
			
			LOGGER.log(Level.SEVERE, "context", e);
		}
	 return c;
	}

	/**
	 * @return the VentureCardArea on the ground floor of the tower.
	 */
	public VentureCardArea getGroundFloor() {
		return v0;
	}

	/**
	 * @return the VentureCardArea on the first floor of the tower.
	 */
	public VentureCardArea getFirstFloor() {
		return v1;
	}

	/**
	 * @return the VentureCardArea on the second floor of the tower.
	 */
	public VentureCardArea getSecondFloor() {
		return v2;
	}

	/**
	 * @return the VentureCardArea on the third floor of the tower.
	 */
	public VentureCardArea getThirdFloor() {
		return v3;
	}
	
	/**
	 * @return an ArrayList containing all the ares in the tower.
	 */
	public ArrayList<VentureCardArea> getAreas(){
		ArrayList<VentureCardArea> list = new ArrayList<VentureCardArea>();
		list.add(v0);
		list.add(v1);
		list.add(v2);
		list.add(v3);
		return list;
	}
	
	/**
	 * @throws Exception if the an area with the given name doesn't exist
	 * @param s name of the area. Also key in the dictionary.
	 * @return A VentureCardArea that corresponds with the parameter string s.
	 */
	public VentureCardArea getAreaFromString(String s) throws Exception {
		if(!dictionary.containsKey(s.toLowerCase())) throw new Exception("No such key");
		return dictionary.get(s.toLowerCase());
	}

	/**
	 * @return The a HashMap which stores the name of an area as key and it's instantiated object as value.
	 */
	public HashMap<String, VentureCardArea> getDictionary() {
		return dictionary;
	}

}
