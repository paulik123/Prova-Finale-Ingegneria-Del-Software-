package it.polimi.ingsw.ps45.model.area;

import it.polimi.ingsw.ps45.model.player.Player;

public class ProductionAreas {
	ProductionAreaSmall small;
	ProductionAreaBig big;
	
	public ProductionAreas(){
		small = new ProductionAreaSmall();
		big = new ProductionAreaBig();
	}
	
	public boolean isOccupiedByPlayer(Player p) {
		return 	    small.isOccupiedByPlayerWithColoredPawn(p) || 
					big.isOccupiedByPlayerWithColoredPawn(p);
	}

	public ProductionAreaSmall getSmall() {
		return small;
	}

	public ProductionAreaBig getBig() {
		return big;
	}
}
