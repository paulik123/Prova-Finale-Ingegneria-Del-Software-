package it.polimi.ingsw.ps45.model.actions.CouncilPrivilege;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;

public class MilitaryPoinsCouncilPrivilege extends CouncilPrivilege{
	
	public MilitaryPoinsCouncilPrivilege(){
		setType("MilitaryPoints");
	}

	@Override
	public ConsumableSet getConsumableSet() {
		ConsumableSet set = new ConsumableSet();
		set.setMilitaryPoins(2);
		return set;
	}

}
