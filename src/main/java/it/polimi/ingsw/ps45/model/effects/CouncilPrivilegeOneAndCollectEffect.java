package it.polimi.ingsw.ps45.model.effects;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.actions.state.CouncilPrivilegeOneState;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

public class CouncilPrivilegeOneAndCollectEffect implements Effect, Serializable{
	ConsumableSet cs;
	
	public CouncilPrivilegeOneAndCollectEffect(ConsumableSet cs){
		this.cs = cs;
	}
	@Override
	public void runEffect(Player p, int value) {
		p.getResourceSet().collect(cs);
		p.getActionBuilder().setState(new CouncilPrivilegeOneState());
	}
}
