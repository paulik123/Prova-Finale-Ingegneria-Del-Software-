package it.polimi.ingsw.ps45.model.actions;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.cards.Building;
import it.polimi.ingsw.ps45.model.cards.BuildingMode;
import it.polimi.ingsw.ps45.model.cards.Territory;
import it.polimi.ingsw.ps45.model.player.Player;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;

public class HarvestNoPawn extends NoPawnAction {
	
	Player p;
	int value;
	
	
	private int servantsAdded;
	
	
	public HarvestNoPawn(Player p, int value){
		this.p = p;
		this.value = value;
	}
	
	

	@Override
	public void run() {
		int level = value + servantsAdded/p.getResourceSet().getActionModifier("servantIncreasePenalty") + p.getResourceSet().getActionModifier("harvest");
		
		ArrayList<Territory> list = p.getResourceSet().getTerritoryList();
		for(int i = 0; i < list.size();i++){
			Territory t = list.get(i);
			if(level >= t.getLevel()) t.harvest(p);
		}
	}


	public void setParameters(int servants) {
		this.servantsAdded = servants;
		calculateCost();
		this.setHasParameters(true);		
	}



	@Override
	protected void calculateCost() {
		cost = new ConsumableSet();
		cost.setConsumable("servants", servantsAdded);
	}

}
