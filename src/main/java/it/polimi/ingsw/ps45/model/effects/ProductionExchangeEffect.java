package it.polimi.ingsw.ps45.model.effects;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

public class ProductionExchangeEffect implements Effect, Serializable {
	
	ConsumableSet before;
	ConsumableSet after;
	
	public ProductionExchangeEffect(ConsumableSet before, ConsumableSet after){
		this.before = before;
		this.after = after;
	}

	@Override
	public void runEffect(Player p, int value) {
		if(p.getResourceSet().hasConsumables(before)){
			p.getResourceSet().getResources().pay(before);
			p.getResourceSet().collect(after);
		}
		
	}

}
