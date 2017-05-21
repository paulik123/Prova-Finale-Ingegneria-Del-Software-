package it.polimi.ingsw.ps45.model.effects;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.actions.state.CouncilPrivilegeOneState;
import it.polimi.ingsw.ps45.model.player.Player;

public class CouncilPrivilegeOneEffect implements Effect, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3542006696048721849L;

	@Override
	public void runEffect(Player p, int value) {
		p.getActionBuilder().setState(new CouncilPrivilegeOneState());
		
	}

}
