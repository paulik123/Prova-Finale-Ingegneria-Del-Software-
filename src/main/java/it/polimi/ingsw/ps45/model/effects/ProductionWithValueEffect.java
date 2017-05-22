package it.polimi.ingsw.ps45.model.effects;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.actions.state.AddServantsToProductionState;
import it.polimi.ingsw.ps45.model.player.Player;

public class ProductionWithValueEffect implements Effect, Serializable{

	private static final long serialVersionUID = 5923931322984603376L;
	int val;
	
	public ProductionWithValueEffect(int val){
		this.val = val;
	}
	@Override
	public void runEffect(Player p, int value) {
		p.getActionBuilder().setState(new AddServantsToProductionState(val));
	}

}