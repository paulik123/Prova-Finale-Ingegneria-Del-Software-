package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.actions.CouncilPrivilege.CouncilPrivilege;
import it.polimi.ingsw.ps45.model.player.Player;

public class CouncilPrivilegeOneAction implements Action{
	
	private Player p;
	CouncilPrivilege cp1;
	
	protected CouncilPrivilegeOneAction(Player p, CouncilPrivilege cp1){
		this.p = p;
		this.cp1 = cp1;
	}
	
	@Override
	public void run() {
		p.getResourceSet().collect(cp1.getConsumableSet());	
	}

}
