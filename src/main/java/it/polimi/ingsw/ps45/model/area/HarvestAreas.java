package it.polimi.ingsw.ps45.model.area;

import it.polimi.ingsw.ps45.model.player.Player;

public class HarvestAreas {
	NoCardArea small;
	NoCardArea big;
	
	public HarvestAreas(){
		small = Board.loadFromFile("HarvestAreaSmall.ser");
		big = Board.loadFromFile("HarvestAreaBig.ser");
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
}
