package it.polimi.ingsw.ps45.model.actions.CouncilPrivilege;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;

/**
 * CouncilPrivilege that can be exchanged with servants.
 */
public class ServantsCouncilPrivilege extends CouncilPrivilege{
	
	/**
 	 * Constructor
 	 * Sets the type as "Servants" in order for it to be used in commands by the players.
	 */
	public ServantsCouncilPrivilege(){
		setType("Servants");
	}

	/**
	 * @return A consumable set containing 2 servants.
	 */
	@Override
	public ConsumableSet getConsumableSet() {
		ConsumableSet set = new ConsumableSet();
		set.setServants(2);
		return set;
	}

}
