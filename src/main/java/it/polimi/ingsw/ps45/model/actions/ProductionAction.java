package it.polimi.ingsw.ps45.model.actions;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.cards.Building;
import it.polimi.ingsw.ps45.model.player.PersonalBonusTile;
import it.polimi.ingsw.ps45.model.player.Player;

public class ProductionAction implements Action{
	private Player p;
	private ProductionMode[] pm;
	private int level;
	
	protected ProductionAction(Player p, ProductionMode[] pm, int level){
		this.p = p;
		this.pm = pm;
		this.level = level;
	}

	@Override
	public void run() {
		ArrayList<Building> buildings = p.getResourceSet().getBuildingList();
		
		PersonalBonusTile bt = p.getResourceSet().getBonusTile();
		if(level >=bt.getProductionLevel())bt.production(p);
		
		for(int i=0; i<buildings.size();i++){
			Building b = buildings.get(i);
			if(level >= b.getProductionLevel()){
				if(pm[i] == ProductionMode.FIRST) b.productionI(p);
				if(pm[i] == ProductionMode.SECOND) b.productionII(p);
			}
		}
	}
	
}
