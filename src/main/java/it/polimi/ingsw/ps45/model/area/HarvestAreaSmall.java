package it.polimi.ingsw.ps45.model.area;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.player.Pawn;
import it.polimi.ingsw.ps45.model.player.Player;

public class HarvestAreaSmall extends HarvestArea {
	
	public HarvestAreaSmall(int cost){
		occupiedBy = new ArrayList<PlayerPawnPair>();
		maxOccupants = 1;
		
		this.setCost(cost);
	}


	

}
