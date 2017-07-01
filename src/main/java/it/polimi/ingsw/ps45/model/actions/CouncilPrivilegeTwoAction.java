package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.actions.CouncilPrivilege.CouncilPrivilege;
import it.polimi.ingsw.ps45.model.actions.state.NoActionState;
import it.polimi.ingsw.ps45.model.player.Player;
/**
 * The actual action that is executed by the player when he wants to exchange two councilPrivileges.
 */
public class CouncilPrivilegeTwoAction implements Action{
	
	private Player p;
	private CouncilPrivilege cp1;
	private CouncilPrivilege cp2;
	
	/**
 	 * Constructor
	 * @param  p the player which executes the action.
	 * @param  cp1 the first CouncilPrivilege the player wants to exchange.
	 * @param  cp2 the second CouncilPrivilege the player wants to exchange.
	 */
	protected CouncilPrivilegeTwoAction(Player p, CouncilPrivilege cp1, CouncilPrivilege cp2){
		this.p = p;
		this.cp1 = cp1;
		this.cp2 = cp2;
	}
	
	/**
 	 * The method that runs the action.
	 */
	@Override
	public void run() {
		p.getResourceSet().collect(cp1.getConsumableSet());	
		p.getResourceSet().collect(cp2.getConsumableSet());	
	}

}
