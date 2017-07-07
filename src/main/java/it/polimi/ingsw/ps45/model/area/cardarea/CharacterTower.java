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
 * Class that holds and manages all CharacterCardAreas of the game.
 */
public class CharacterTower implements Tower, HasDictionary{
	private static final Logger LOGGER = Logger.getLogger( CharacterTower.class.getName());
	private CharacterCardArea c0;
	private CharacterCardArea c1;
	private CharacterCardArea c2;
	private CharacterCardArea c3;
	
	private HashMap<String, CharacterCardArea> dictionary;
	
	/**
 	 * Constructor
 	 * Reads all the areas from file.
	 */
	public CharacterTower(){
		dictionary = new HashMap<String, CharacterCardArea>();
		
		c0 = loadFromFile("serialized/areas/cardareas/CharacterCardAreaGroundFloor.json");
		c1 = loadFromFile("serialized/areas/cardareas/CharacterCardAreaFirstFloor.json");
		c2 = loadFromFile("serialized/areas/cardareas/CharacterCardAreaSecondFloor.json");
		c3 = loadFromFile("serialized/areas/cardareas/CharacterCardAreaThirdFloor.json");
		
		dictionary.put("c0", c0);
		dictionary.put("c1", c1);
		dictionary.put("c2", c2);
		dictionary.put("c3", c3);
		
	}
	

	
	/**
	 * @return true if any of the areas in the tower is occupied.
	 */
	@Override
	public boolean isOccupied() {
		int occupants = c0.getOccupants().size() + c1.getOccupants().size() + c2.getOccupants().size() + c3.getOccupants().size(); 
		if(occupants > 0)return true;
		return false;
	}

	
	/**
	 * @param p the player that the area will be checked if is occupied by.
	 * @return true if any of the areas in the tower is occupied by the player in the parameters.
	 */
	@Override
	public boolean isOccupiedByPlayer(Player p) {
		return 	    c0.isOccupiedByPlayerWithColoredPawn(p) || 
					c1.isOccupiedByPlayerWithColoredPawn(p) || 
					c2.isOccupiedByPlayerWithColoredPawn(p) || 
					c3.isOccupiedByPlayerWithColoredPawn(p);
	}
	
	/**
	 * Loads a CharacterCardAreas from file.
	 * @param path the path of the serialized json area file.
	 */
	public CharacterCardArea loadFromFile(String path){
		Gson gson = new GsonBuilder()
                .registerTypeAdapter(Effect.class,
                        new PropertyBasedInterfaceMarshal()).create();
	 
		CharacterCardArea c = null;
		try {
			c = gson.fromJson(new FileReader(path), CharacterCardArea.class);
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
	 * @return the CharacterCardArea on the ground floor of the tower.
	 */
	public CharacterCardArea getGroundFloor() {
		return c0;
	}

	/**
	 * @return the CharacterCardArea on the first floor of the tower.
	 */
	public CharacterCardArea getFirstFloor() {
		return c1;
	}

	/**
	 * @return the CharacterCardArea on the second floor of the tower.
	 */
	public CharacterCardArea getSecondFloor() {
		return c2;
	}

	/**
	 * @return the CharacterCardArea on the third floor of the tower.
	 */
	public CharacterCardArea getThirdFloor() {
		return c3;
	}
	
	/**
	 * @return an ArrayList containing all the ares in the tower.
	 */
	public ArrayList<CharacterCardArea> getAreas(){
		ArrayList<CharacterCardArea> list = new ArrayList<CharacterCardArea>();
		list.add(c0);
		list.add(c1);
		list.add(c2);
		list.add(c3);
		return list;
	}
	
	/**
	 * @throws Exception if the an area with the given name doesn't exist
	 * @param s name of the area. Also key in the dictionary.
	 * @return A CharacterCardArea that corresponds with the parameter string s.
	 */
	public CharacterCardArea getAreaFromString(String s) throws Exception {
		if(!dictionary.containsKey(s.toLowerCase())) throw new Exception("No such key");
		return dictionary.get(s.toLowerCase());
	}

	/**
	 * @return The a HashMap which stores the name of an area as key and it's instantiated object as value.
	 */
	public HashMap<String, CharacterCardArea> getDictionary() {
		return dictionary;
	}

}
