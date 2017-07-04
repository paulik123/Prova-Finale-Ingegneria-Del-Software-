package it.polimi.ingsw.ps45.model.effects;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * An effect that makes buildins cost less when the player tries to acquire them.
 */
public class TakeBuildingResourceDiscountEffect implements Effect{

	private ConsumableSet discount;
	
	/**
 	 * Constructor
 	 * @param discount a ConsumableSet that represents the discount a player will have when acquiring a building.
	 */
	public TakeBuildingResourceDiscountEffect(ConsumableSet discount){
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
		p.getResourceSet().getBuildingActionDiscount().collect(discount);
	}

}
