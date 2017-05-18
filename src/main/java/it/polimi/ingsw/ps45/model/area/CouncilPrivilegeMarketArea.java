package it.polimi.ingsw.ps45.model.area;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

public class CouncilPrivilegeMarketArea extends NoCardArea{

	private static int COST = 1;
	private static int OCCUPANTS = 1;
	
	public CouncilPrivilegeMarketArea(){
		
		maxOccupants = OCCUPANTS;
		

		this.setCost(COST);
	}

	@Override
	public void immediateEffect(Player p) {
		//TODO ab.MakeCouncilPrivilegeAction
	}

}
