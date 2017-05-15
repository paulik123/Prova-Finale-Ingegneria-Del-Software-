package it.polimi.ingsw.ps45.model.area;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.CouncilPrivilegeType;
import it.polimi.ingsw.ps45.model.player.Player;

public class CouncilPalaceArea extends Area {
	
	private static int coinsReward  = 1;
	
	public CouncilPalaceArea(int cost){
		occupiedBy = new ArrayList<PlayerPawnPair>();
		maxOccupants = 999;
		
		this.setCost(cost);
	}
	
	public void immediateEffect(Player p, CouncilPrivilegeType cpt){
		ConsumableSet cs = new ConsumableSet();
		cs.setConsumable("coins", coinsReward);
		p.getResourceSet().collect(cs);
		p.getResourceSet().collect(CouncilPrivilegeType.getConsumableSet(cpt));
	}
}
