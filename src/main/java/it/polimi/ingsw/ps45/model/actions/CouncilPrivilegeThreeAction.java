package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.actions.CouncilPrivilege.CouncilPrivilege;
import it.polimi.ingsw.ps45.model.player.Player;
/**
 * The actual action that is executed by the player when he wants to exchange three councilPrivileges.
 */
public class CouncilPrivilegeThreeAction implements Action{
	
	private Player p;
	private CouncilPrivilege cp1;
	private CouncilPrivilege cp2;
	private CouncilPrivilege cp3;
	
	/**
 	 * Constructor
	 * @param  p the player which executes the action.
	 * @param  cp1 the first CouncilPrivilege the player wants to exchange.
	 * @param  cp2 the second CouncilPrivilege the player wants to exchange.
	 * @param  cp3 the third CouncilPrivilege the player wants to exchange.
	 */
	protected CouncilPrivilegeThreeAction(Player p, CouncilPrivilege cp1, CouncilPrivilege cp2, CouncilPrivilege cp3){
		this.p = p;
		this.cp1 = cp1;
		this.cp2 = cp2;
		this.cp3 = cp3;
	}
	
	/**
 	 * The method that runs the action.
	 */
	@Override
	public void run() {
		p.getResourceSet().collect(cp1.getConsumableSet());	
		p.getResourceSet().collect(cp2.getConsumableSet());	
		p.getResourceSet().collect(cp3.getConsumableSet());
	}

}
