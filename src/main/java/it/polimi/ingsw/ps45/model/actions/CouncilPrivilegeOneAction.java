package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.actions.CouncilPrivilege.CouncilPrivilege;
import it.polimi.ingsw.ps45.model.actions.state.NoActionState;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * The actual action that is executed by the player when he wants to exchange a councilPrivilege.
 */
public class CouncilPrivilegeOneAction implements Action{
	
	private Player p;
	CouncilPrivilege cp1;
	
	/**
 	 * Constructor
	 * @param  p the player which executes the action.
	 * @param  cp1 the CouncilPrivilege the player wants to exchange.
	 */
	protected CouncilPrivilegeOneAction(Player p, CouncilPrivilege cp1){
		this.p = p;
		this.cp1 = cp1;
	}
	
	@Override
	public void run() {
		p.getResourceSet().collect(cp1.getConsumableSet());	
	}

}
