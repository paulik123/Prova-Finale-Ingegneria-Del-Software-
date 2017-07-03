package it.polimi.ingsw.ps45.model.effects;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.actions.state.CouncilPrivilegeOneState;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * An effect that makes a player collect a consumableset reward and also exchange a councilprivilege.
 */
public class CouncilPrivilegeOneAndCollectEffect implements Effect{
	private ConsumableSet cs;
	
	/**
 	 * Constructor
 	 * @param cs The ConsumableSet that the player will collect.
	 */
	public CouncilPrivilegeOneAndCollectEffect(ConsumableSet cs){
		this.cs = cs;
	}
	
	/**
	 * The method that runs the effect.
	 * 
	 *@param p the player that runs the effect.
	 *@param value the value of the effect. It is needed only when nesting actions(an action makes you do another action with a certain value).
	 */
	@Override
	public void runEffect(Player p, int value) {
		p.getResourceSet().collect(cs);
		p.getActionBuilder().setState(new CouncilPrivilegeOneState());
	}
}
