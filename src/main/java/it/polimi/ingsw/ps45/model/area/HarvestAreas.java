package it.polimi.ingsw.ps45.model.area;

import it.polimi.ingsw.ps45.model.player.Player;

public class HarvestAreas {
	HarvestAreaSmall small;
	HarvestAreaBig big;
	
	public HarvestAreas(){
		small = new HarvestAreaSmall();
		big = new HarvestAreaBig();
	}
	
	public boolean isOccupiedByPlayer(Player p) {
		return 	    small.isOccupiedByPlayerWithColoredPawn(p) || 
					big.isOccupiedByPlayerWithColoredPawn(p);
	}

	public HarvestAreaSmall getSmall() {
		return small;
	}

	public HarvestAreaBig getBig() {
		return big;
	}
}
