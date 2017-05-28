package it.polimi.ingsw.ps45.model.area;

import java.util.HashMap;

import it.polimi.ingsw.ps45.model.player.Player;

public class ProductionAreas {
	NoCardArea small;
	NoCardArea big;
	HashMap<String, NoCardArea> productionAreaDictionary;
	
	public ProductionAreas(){
		productionAreaDictionary = new HashMap<String, NoCardArea>();
		small = Board.loadFromFile("serialized//areas//ProductionSmallArea.json");
		big = Board.loadFromFile("serialized//areas//ProductionBigArea.json");
		productionAreaDictionary.put("small", small);
		productionAreaDictionary.put("big", big);
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
		if(!productionAreaDictionary.containsKey(s.toLowerCase())) throw new Exception("No such key");
		return productionAreaDictionary.get(s.toLowerCase());
	}
}
