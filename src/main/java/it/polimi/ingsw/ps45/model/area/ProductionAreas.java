package it.polimi.ingsw.ps45.model.area;

import it.polimi.ingsw.ps45.model.player.Player;

public class ProductionAreas {
	NoCardArea small;
	NoCardArea big;
	
	public ProductionAreas(){
		small = Board.loadFromFile("ProductionAreaSmall.ser");
		big = Board.loadFromFile("ProductionAreaBig.ser");
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
