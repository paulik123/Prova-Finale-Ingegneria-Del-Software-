package it.polimi.ingsw.ps45.model.area;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.player.Pawn;
import it.polimi.ingsw.ps45.model.player.Player;

public class HarvestAreaBig extends NoCardArea {

	private static int bigHarvestPenalty = 3;
	private static int COST = 1;
	private static int OCCUPANTS = 999;
	
	public HarvestAreaBig(){
		maxOccupants = OCCUPANTS;
		
		this.setCost(COST);
	}

	@Override
	public void immediateEffect(Player p) {
		//TODO: ab.setHarvestAction - bigHarvestPenalty
	}




	
}
