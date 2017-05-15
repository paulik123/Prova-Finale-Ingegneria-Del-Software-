package it.polimi.ingsw.ps45.model.actions;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.area.ProductionAreaBig;
import it.polimi.ingsw.ps45.model.cards.Building;
import it.polimi.ingsw.ps45.model.cards.BuildingMode;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Pawn;
import it.polimi.ingsw.ps45.model.player.Player;

public class ProductionNoPawn extends NoPawnAction {
	
	Player p;
	int value;
	
	
	private int servantsAdded;
	BuildingMode[] modes;
	
	
	public ProductionNoPawn(Player p, int value){
		this.p = p;
		this.value = value;
	}
	
	

	@Override
	public void run() {
		int level = value + servantsAdded/p.getResourceSet().getActionModifier("servantIncreasePenalty") + p.getResourceSet().getActionModifier("production");
		
		ArrayList<Building> list = p.getResourceSet().getBuildingList();
		for(int i = 0; i < list.size();i++){
			Building b = list.get(i);
			if(level >= b.getLevel()) b.production(p, modes[i]);
		}
	}


	public void setParameters(BuildingMode[] modes, int servants) {
		this.modes = modes;
		this.servantsAdded = servants;
		this.setHasParameters(true);
	}



	@Override
	protected void calculateCost() {
		cost = new ConsumableSet();
		cost.setConsumable("servants", servantsAdded);
	}
	

}
