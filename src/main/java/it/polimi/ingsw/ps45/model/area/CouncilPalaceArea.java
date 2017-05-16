package it.polimi.ingsw.ps45.model.area;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.CouncilPrivilegeType;
import it.polimi.ingsw.ps45.model.player.Player;

public class CouncilPalaceArea extends Area {
	
	ConsumableSet immediateEffectCS;
	
	public CouncilPalaceArea(ConsumableSet cs ,int cost){
		occupiedBy = new ArrayList<PlayerPawnPair>();
		maxOccupants = 999;
		
		immediateEffectCS = cs;

		this.setCost(cost);
	}
	

}
