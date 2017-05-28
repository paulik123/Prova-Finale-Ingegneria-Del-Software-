package it.polimi.ingsw.ps45.model.effects;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.actions.state.ProductionState;
import it.polimi.ingsw.ps45.model.player.Player;

public class ProductionEffect implements Effect, Serializable{
	int penalty;
	
	public ProductionEffect(int penalty){
		this.penalty = penalty;
	}

	@Override
	public void runEffect(Player p, int value) {
		p.getActionBuilder().setState(new ProductionState(value - penalty));
		p.setStatus("Make a production");
	}
}
