package it.polimi.ingsw.ps45.model.area;

import java.util.HashMap;

import it.polimi.ingsw.ps45.model.player.Player;

/**
 * Class that holds and manages all production areas of the game.
 */
public class ProductionAreas implements HasDictionary {
	private NoCardArea small;
	private NoCardArea big;
	private HashMap<String, NoCardArea> productionAreaDictionary;
	
	/**
 	 * Constructor
 	 * Reads all the production areas from file.
 	 * @param players the number of players (because if players < 4 some areas don't need to be instantiated)
	 */
	public ProductionAreas(int players){
		productionAreaDictionary = new HashMap<String, NoCardArea>();
		small = Board.loadFromFile("serialized/areas/ProductionSmallArea.json");
		big = Board.loadFromFile("serialized/areas/ProductionBigArea.json");
		productionAreaDictionary.put("small", small);
		if(players >= 3)productionAreaDictionary.put("big", big);
	}
	
	/**
	 * @param p The player that the area is checked to be occupied by.
	 * @return True if the area is occupied by the player object in the arguments.
	 */
	public boolean isOccupiedByPlayer(Player p) {
		return 	    small.isOccupiedByPlayerWithColoredPawn(p) || 
					big.isOccupiedByPlayerWithColoredPawn(p);
	}

	/**
	 * @return The small production area.
	 */
	public NoCardArea getSmall() {
		return small;
	}

	/**
	 * @return The big production area.
	 */
	public NoCardArea getBig() {
		return big;
	}
	
	/**
	 * @throws Exception if the an area with the given name doesn't exist
	 * @param s name of the area. Also key in the dictionary.
	 * @return A NoCardArea that corresponds with the parameter string s.
	 */
	public NoCardArea getAreaFromString(String s) throws Exception {
		if(!productionAreaDictionary.containsKey(s.toLowerCase())) throw new Exception("No such key");
		return productionAreaDictionary.get(s.toLowerCase());
	}

	/**
	 * @return The a HashMap which stores the name of an area as key and it's instantiated object as value.
	 */
	public HashMap<String, NoCardArea> getDictionary() {
		return productionAreaDictionary;
	}

	
	
}
