package it.polimi.ingsw.ps45.model.area;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.player.Pawn;
import it.polimi.ingsw.ps45.model.player.Player;

public class HarvestAreaBig extends Area {

	private static int bigProductionPenalty = 3;
	
	public HarvestAreaBig(int cost){
		occupiedBy = new ArrayList<Player>();
		maxOccupants = 999;
		
		this.setCost(cost);
	}

	public void immediateEffect(Player p, Pawn pawn, int servantsAdded) {
		pawn.setValue(pawn.getValue() - bigProductionPenalty);
		p.harvest(pawn, servantsAdded);
	}

	@Override
	public boolean isAvailable() {
		return true;
	}
	
}
