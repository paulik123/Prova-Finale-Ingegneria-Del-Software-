package it.polimi.ingsw.ps45.model.effects;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.actions.state.CouncilPrivilegeOneState;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

public class ProductionExchangeConsumableSetCouncilPrivilegeEffect implements Effect, Serializable {
	
	ConsumableSet before;
	
	public ProductionExchangeConsumableSetCouncilPrivilegeEffect(ConsumableSet before){
		this.before = before;
	}

	@Override
	public void runEffect(Player p, int value) {
		if(p.getResourceSet().hasConsumables(before)){
			p.getResourceSet().getResources().pay(before);
			p.getActionBuilder().setState(new CouncilPrivilegeOneState());
		}
		
	}

}
