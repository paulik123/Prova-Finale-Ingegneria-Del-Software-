package it.polimi.ingsw.ps45.model.area;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.player.Pawn;
import it.polimi.ingsw.ps45.model.player.Player;

public class ProductionAreaSmall extends Area {
	
	public ProductionAreaSmall(int cost){
		occupiedBy = new ArrayList<PlayerPawnPair>();
		maxOccupants = 1;
		
		this.setCost(cost);
	}
}
