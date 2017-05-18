package it.polimi.ingsw.ps45.model.actions.CouncilPrivilege;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;

public class CoinsCouncilPrivilege extends CouncilPrivilege{
	
	public CoinsCouncilPrivilege(){
		setType("Coins");
	}

	@Override
	public ConsumableSet getConsumableSet() {
		ConsumableSet set = new ConsumableSet();
		set.setCoins(2);
		return set;
	}

}
