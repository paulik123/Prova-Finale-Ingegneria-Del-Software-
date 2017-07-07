package it.polimi.ingsw.ps45.model.area;

import java.util.HashMap;

import it.polimi.ingsw.ps45.model.player.Player;

/**
 * Class that holds and manages all harvest areas of the game.
 */
public class HarvestAreas implements HasDictionary {
	private NoCardArea small;
	private NoCardArea big;
	private HashMap<String, NoCardArea> harvestAreaDictionary;
	
	/**
 	 * Constructor
 	 * Reads all the harvest areas from file.
 	 * @param players the number of players (because if players < 4 some areas don't need to be instantiated)
	 */
	public HarvestAreas(int players){
		harvestAreaDictionary = new HashMap<String, NoCardArea>(); 
		small = Board.loadFromFile("serialized/areas/HarvestSmallArea.json");
		big = Board.loadFromFile("serialized/areas/HarvestBigArea.json");
		
		harvestAreaDictionary.put("small", small);
		if(players >= 3)harvestAreaDictionary.put("big", big);
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
	 * @return The small Harvest area.
	 */
	public NoCardArea getSmall() {
		return small;
	}

	/**
	 * @return The big Harvest area.
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
		if(!harvestAreaDictionary.containsKey(s.toLowerCase())) throw new Exception("No such key");
		return harvestAreaDictionary.get(s.toLowerCase());
	}

	/**
	 * @return The a HashMap which stores the name of an area as key and it's instantiated object as value.
	 */
	public HashMap<String, NoCardArea> getDictionary() {
		return harvestAreaDictionary;
	}

}
