package it.polimi.ingsw.ps45.model.area;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

public class CouncilPalaceArea extends NoCardArea {
	
	private static int COST = 1;
	private static int COINSREWARD = 1;
	private static int MAXOCCUPANTS = 999;
	

	
	public CouncilPalaceArea(){
		maxOccupants = MAXOCCUPANTS;
		this.setCost(COST);
	}

	@Override
	public void immediateEffect(Player p, int value) {
		ConsumableSet cs = new ConsumableSet();
		cs.setCoins(COINSREWARD);
	//TODO: ab.setCouncilPrivilegeAction
		
	}
	

}
