package it.polimi.ingsw.ps45.model.actions.CouncilPrivilege;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;

/**
 * CouncilPrivilege that can be exchanged with military points.
 */
public class MilitaryPoinsCouncilPrivilege extends CouncilPrivilege{
	
	/**
 	 * Constructor
 	 * Sets the type as "MilitaryPoints" in order for it to be used in commands by the players.
	 */
	public MilitaryPoinsCouncilPrivilege(){
		setType("MilitaryPoints");
	}

	/**
	 * @return A consumable set containing 2 military points.
	 */
	@Override
	public ConsumableSet getConsumableSet() {
		ConsumableSet set = new ConsumableSet();
		set.setMilitaryPoins(2);
		return set;
	}

}
