package it.polimi.ingsw.ps45.model.area;

import java.util.HashMap;

import it.polimi.ingsw.ps45.model.player.Player;

public class HarvestAreas {
	NoCardArea small;
	NoCardArea big;
	HashMap<String, NoCardArea> harvestAreaDictionary;
	
	public HarvestAreas(){
		harvestAreaDictionary = new HashMap<String, NoCardArea>(); 
		small = Board.loadFromFile("serialized//areas//HarvestSmallArea.json");
		big = Board.loadFromFile("serialized//areas//HarvestBigArea.json");
		
		harvestAreaDictionary.put("small", small);
		harvestAreaDictionary.put("big", big);
	}
	
	public boolean isOccupiedByPlayer(Player p) {
		return 	    small.isOccupiedByPlayerWithColoredPawn(p) || 
					big.isOccupiedByPlayerWithColoredPawn(p);
	}

	public NoCardArea getSmall() {
		return small;
	}

	public NoCardArea getBig() {
		return big;
	}

	public NoCardArea getAreaFromString(String s) throws Exception {
		if(!harvestAreaDictionary.containsKey(s.toLowerCase())) throw new Exception("No such key");
		return harvestAreaDictionary.get(s.toLowerCase());
	}
	
	
}
