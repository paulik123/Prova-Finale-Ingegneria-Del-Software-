package it.polimi.ingsw.ps45.model.actions.CouncilPrivilege;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;

public class FaithPointsCouncilPrivilege extends CouncilPrivilege{
	
	public FaithPointsCouncilPrivilege(){
		setType("FaithPoints");
	}

	@Override
	public ConsumableSet getConsumableSet() {
		ConsumableSet set = new ConsumableSet();
		set.setFaithPoints(1);
		return set;
	}

}
