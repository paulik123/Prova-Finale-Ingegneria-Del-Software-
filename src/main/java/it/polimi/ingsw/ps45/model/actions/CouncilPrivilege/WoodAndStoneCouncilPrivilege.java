package it.polimi.ingsw.ps45.model.actions.CouncilPrivilege;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;

/**
 * CouncilPrivilege that can be exchanged with wood and stone.
 */
public class WoodAndStoneCouncilPrivilege extends CouncilPrivilege{
	
	/**
 	 * Constructor
 	 * Sets the type as "WoodAndStone" in order for it to be used in commands by the players.
	 */
	public WoodAndStoneCouncilPrivilege(){
		setType("WoodAndStone");
	}

	/**
	 * @return A consumable set containing 1 wood and one stone.
	 */
	@Override
	public ConsumableSet getConsumableSet() {
		ConsumableSet set = new ConsumableSet();
		set.setStone(1);
		set.setWood(1);
		return set;
	}

}
