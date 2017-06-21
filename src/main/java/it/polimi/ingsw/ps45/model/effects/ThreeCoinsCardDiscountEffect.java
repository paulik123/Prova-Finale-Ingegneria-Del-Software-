package it.polimi.ingsw.ps45.model.effects;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

public class ThreeCoinsCardDiscountEffect implements Effect{
	ConsumableSet discount;
	
	public ThreeCoinsCardDiscountEffect(ConsumableSet discount){
		this.discount = discount;
	}
	
	@Override
	public void runEffect(Player p, int value) {
		p.getResourceSet().getTerritoryActionDiscount().collect(discount);
		p.getResourceSet().getCharacterActionDiscount().collect(discount);
		p.getResourceSet().getBuildingActionDiscount().collect(discount);
		p.getResourceSet().getVentureActionDiscount().collect(discount);
	}
}
