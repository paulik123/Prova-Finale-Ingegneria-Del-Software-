package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.actions.CouncilPrivilege.CouncilPrivilege;
import it.polimi.ingsw.ps45.model.actions.state.NoActionState;
import it.polimi.ingsw.ps45.model.player.Player;

public class CouncilPrivilegeTwoAction implements Action{
	
	private Player p;
	CouncilPrivilege cp1;
	CouncilPrivilege cp2;
	
	protected CouncilPrivilegeTwoAction(Player p, CouncilPrivilege cp1, CouncilPrivilege cp2){
		this.p = p;
		this.cp1 = cp1;
		this.cp2 = cp2;
	}
	
	@Override
	public void run() {
		p.getResourceSet().collect(cp1.getConsumableSet());	
		p.getResourceSet().collect(cp2.getConsumableSet());	
		p.getActionBuilder().setState(new NoActionState());
	}

}
