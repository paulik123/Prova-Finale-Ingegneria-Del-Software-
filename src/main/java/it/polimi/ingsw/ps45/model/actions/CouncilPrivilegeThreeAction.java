package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.actions.CouncilPrivilege.CouncilPrivilege;
import it.polimi.ingsw.ps45.model.player.Player;

public class CouncilPrivilegeThreeAction implements Action{
	
	private Player p;
	CouncilPrivilege cp1;
	CouncilPrivilege cp2;
	CouncilPrivilege cp3;
	
	protected CouncilPrivilegeThreeAction(Player p, CouncilPrivilege cp1, CouncilPrivilege cp2, CouncilPrivilege cp3){
		this.p = p;
		this.cp1 = cp1;
		this.cp2 = cp2;
		this.cp3 = cp3;
	}
	
	@Override
	public void run() {
		p.getResourceSet().collect(cp1.getConsumableSet());	
		p.getResourceSet().collect(cp2.getConsumableSet());	
		p.getResourceSet().collect(cp3.getConsumableSet());
	}

}