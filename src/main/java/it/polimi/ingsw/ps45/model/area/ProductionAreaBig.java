package it.polimi.ingsw.ps45.model.area;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.player.Player;

public class ProductionAreaBig extends Area {
	
	private static int bigProductionPenalty = 3;
	
	public ProductionAreaBig(int cost){
		occupiedBy = new ArrayList<Player>();
		maxOccupants = 999;
		
		this.setCost(cost);
	}

	public void immediateEffect(Player p, int pawnValue) {
		p.production(pawnValue - bigProductionPenalty);
	}

	@Override
	public boolean isAvailable() {
		return true;
	}
	
	
	
	
}