package it.polimi.ingsw.ps45.model.effects;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.actions.state.CouncilPrivilegeOneState;
import it.polimi.ingsw.ps45.model.actions.state.CouncilPrivilegeThreeState;
import it.polimi.ingsw.ps45.model.player.Player;

public class CouncilPrivilegeThreeEffect implements Effect, Serializable{



	@Override
	public void runEffect(Player p, int value) {
		p.getActionBuilder().setState(new CouncilPrivilegeThreeState());
		
	}

}
