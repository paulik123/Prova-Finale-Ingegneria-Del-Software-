package it.polimi.ingsw.ps45.model.actions.CouncilPrivilege;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;

public class WoodAndStoneCouncilPrivilege extends CouncilPrivilege{
	
	public WoodAndStoneCouncilPrivilege(){
		setType("WoodAndStone");
	}

	@Override
	public ConsumableSet getConsumableSet() {
		ConsumableSet set = new ConsumableSet();
		set.setStone(1);
		set.setWood(1);
		return set;
	}

}
