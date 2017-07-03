package it.polimi.ingsw.ps45.model.effects;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * An odd effect that makes the player exchange consumables with other consumables
 */
public class ProductionExchangeEffect implements Effect, Serializable {
	
	private ConsumableSet before;
	private ConsumableSet after;
	
	/**
 	 * Constructor
 	 * @param before the ConsumableSet that the player has to "pay" for the exchange.
 	 * @param after the ConsumableSet that the player will "collect" after the exchange.
	 */
	public ProductionExchangeEffect(ConsumableSet before, ConsumableSet after){
		this.before = before;
		this.after = after;
	}

	/**
	 * The method that runs the effect.
	 * 
	 *@param p the player that runs the effect.
	 *@param value the value of the effect. It is needed only when nesting actions(an action makes you do another action with a certain value).
	 */
	@Override
	public void runEffect(Player p, int value) {
		if(p.getResourceSet().hasConsumables(before)){
			p.getResourceSet().getResources().pay(before);
			p.getResourceSet().collect(after);
		}
		
	}

}
