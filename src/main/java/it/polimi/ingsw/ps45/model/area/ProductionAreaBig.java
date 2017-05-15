package it.polimi.ingsw.ps45.model.area;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.player.Pawn;
import it.polimi.ingsw.ps45.model.player.Player;

public class ProductionAreaBig extends Area {
	
	private static int bigProductionPenalty = 3;
	
	public ProductionAreaBig(int cost){
		occupiedBy = new ArrayList<PlayerPawnPair>();
		maxOccupants = 999;
		
		this.setCost(cost);
	}



	
	
	
	
}
