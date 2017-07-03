package it.polimi.ingsw.ps45.model.effects;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * An effect that makes all card cost less(discount) for the player.
 */
public class ThreeCoinsCardDiscountEffect implements Effect{
	private ConsumableSet discount;
	
	/**
 	 * Constructor
 	 * @param discount a ConsumableSet that represents the discount.
	 */
	public ThreeCoinsCardDiscountEffect(ConsumableSet discount){
		this.discount = discount;
	}
	
	/**
	 * The method that runs the effect.
	 * 
	 *@param p the player that runs the effect.
	 *@param value the value of the effect. It is needed only when nesting actions(an action makes you do another action with a certain value).
	 */
	@Override
	public void runEffect(Player p, int value) {
		p.getResourceSet().getTerritoryActionDiscount().collect(discount);
		p.getResourceSet().getCharacterActionDiscount().collect(discount);
		p.getResourceSet().getBuildingActionDiscount().collect(discount);
		p.getResourceSet().getVentureActionDiscount().collect(discount);
	}
}
