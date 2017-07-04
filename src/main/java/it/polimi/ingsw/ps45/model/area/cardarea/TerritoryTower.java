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
 * Class that holds and manages all TerritoryCardAreas of the game.
 */
public class TerritoryTower implements Tower, HasDictionary{
	private static final Logger LOGGER = Logger.getLogger( TerritoryTower.class.getName());
	private HashMap<String, TerritoryCardArea> dictionary;
	private TerritoryCardArea t0;
	private TerritoryCardArea t1;
	private TerritoryCardArea t2;
	private TerritoryCardArea t3;
	
	
	/**
 	 * Constructor
 	 * Reads all the areas from file.
	 */
	public TerritoryTower(){
		dictionary = new HashMap<String, TerritoryCardArea>();
		
		t0 = loadFromFile("serialized//areas//cardareas//TerritoryCardAreaGroundFloor.json");
		t1 = loadFromFile("serialized//areas//cardareas//TerritoryCardAreaFirstFloor.json");
		t2 = loadFromFile("serialized//areas//cardareas//TerritoryCardAreaSecondFloor.json");
		t3 = loadFromFile("serialized//areas//cardareas//TerritoryCardAreaThirdFloor.json");
		
		dictionary.put("t0", t0);
		dictionary.put("t1", t1);
		dictionary.put("t2", t2);
		dictionary.put("t3", t3);
	}
	

	/**
	 * @return true if any of the areas in the tower is occupied.
	 */
	@Override
	public boolean isOccupied() {
		int occupants = t0.getOccupants().size() + t1.getOccupants().size() + t2.getOccupants().size() + t3.getOccupants().size(); 
		if(occupants > 0)return true;
		return false;
	}

	/**
	 * @param p the player that the area will be checked if is occupied by.
	 * @return true if any of the areas in the tower is occupied by the player in the parameters.
	 */
	@Override
	public boolean isOccupiedByPlayer(Player p) {
		return 	    t0.isOccupiedByPlayerWithColoredPawn(p) || 
					t1.isOccupiedByPlayerWithColoredPawn(p) || 
					t2.isOccupiedByPlayerWithColoredPawn(p) || 
					t3.isOccupiedByPlayerWithColoredPawn(p);
	}
	
	/**
	 * Loads a TerritoryCardAreas from file.
	 * @param path the path of the serialized json area file.
	 */
	public TerritoryCardArea loadFromFile(String path){
		Gson gson = new GsonBuilder()
                .registerTypeAdapter(Effect.class,
                        new PropertyBasedInterfaceMarshal()).create();
	 
		TerritoryCardArea c = null;
		try {
			c = gson.fromJson(new FileReader(path), TerritoryCardArea.class);
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
	 * @return the TerritoryCardArea on the ground floor of the tower.
	 */
	public TerritoryCardArea getGroundFloor() {
		return t0;
	}

	/**
	 * @return the TerritoryCardArea on the first floor of the tower.
	 */
	public TerritoryCardArea getFirstFloor() {
		return t1;
	}

	/**
	 * @return the TerritoryCardArea on the second floor of the tower.
	 */
	public TerritoryCardArea getSecondFloor() {
		return t2;
	}

	/**
	 * @return the TerritoryCardArea on the third floor of the tower.
	 */
	public TerritoryCardArea getThirdFloor() {
		return t3;
	}
	
	/**
	 * @return an ArrayList containing all the ares in the tower.
	 */
	public ArrayList<TerritoryCardArea> getAreas(){
		ArrayList<TerritoryCardArea> list = new ArrayList<TerritoryCardArea>();
		list.add(t0);
		list.add(t1);
		list.add(t2);
		list.add(t3);
		return list;
	}
	
	/**
	 * @throws Exception if the an area with the given name doesn't exist
	 * @param s name of the area. Also key in the dictionary.
	 * @return A TerritoryCardArea that corresponds with the parameter string s.
	 */
	public TerritoryCardArea getAreaFromString(String s) throws Exception {
		if(!dictionary.containsKey(s.toLowerCase())) throw new Exception("No such key");
		return dictionary.get(s.toLowerCase());
	}

	
	/**
	 * @return The a HashMap which stores the name of an area as key and it's instantiated object as value.
	 */
	public HashMap<String, TerritoryCardArea> getDictionary() {
		return dictionary;
	}


}
