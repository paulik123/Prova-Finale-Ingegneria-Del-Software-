package it.polimi.ingsw.ps45.model.actions.CouncilPrivilege;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;

/**
 * CouncilPrivilege that can be exchanged with coins
 */
public class CoinsCouncilPrivilege extends CouncilPrivilege{
	
	/**
 	 * Constructor
 	 * Sets the type as "coins" in order for it to be used in commands by the players.
	 */
	public CoinsCouncilPrivilege(){
		setType("Coins");
	}

	/**
	 * @return A consumable set containing two coins
	 */

	@Override
	public ConsumableSet getConsumableSet() {
		ConsumableSet set = new ConsumableSet();
		set.setCoins(2);
		return set;
	}

}
