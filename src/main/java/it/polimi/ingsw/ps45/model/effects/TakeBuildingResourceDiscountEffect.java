package it.polimi.ingsw.ps45.model.effects;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

public class TakeBuildingResourceDiscountEffect implements Effect, Serializable{

	ConsumableSet discount;
	
	public TakeBuildingResourceDiscountEffect(ConsumableSet discount){
		this.discount = discount;
	}
	
	@Override
	public void runEffect(Player p, int value) {
		p.getResourceSet().getBuildingActionDiscount().collect(discount);
	}

}
