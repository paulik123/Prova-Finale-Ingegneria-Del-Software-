package it.polimi.ingsw.ps45.model.actions.CouncilPrivilege;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;

public class ServantsCouncilPrivilege extends CouncilPrivilege{
	
	public ServantsCouncilPrivilege(){
		setType("Servants");
	}

	@Override
	public ConsumableSet getConsumableSet() {
		ConsumableSet set = new ConsumableSet();
		set.setServants(2);
		return set;
	}

}
