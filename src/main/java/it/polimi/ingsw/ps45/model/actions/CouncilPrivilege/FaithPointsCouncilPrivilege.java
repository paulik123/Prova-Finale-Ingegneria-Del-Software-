package it.polimi.ingsw.ps45.model.actions.CouncilPrivilege;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;

/**
 * CouncilPrivilege that can be exchanged with faithPoints.
 */
public class FaithPointsCouncilPrivilege extends CouncilPrivilege{
	
	/**
 	 * Constructor
 	 * Sets the type as "FaithPoints" in order for it to be used in commands by the players.
	 */
	public FaithPointsCouncilPrivilege(){
		setType("FaithPoints");
	}

	/**
	 * @return A consumable set containing 1 faith point.
	 */
	@Override
	public ConsumableSet getConsumableSet() {
		ConsumableSet set = new ConsumableSet();
		set.setFaithPoints(1);
		return set;
	}

}
