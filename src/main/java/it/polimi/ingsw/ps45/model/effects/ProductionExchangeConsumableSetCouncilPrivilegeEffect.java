package it.polimi.ingsw.ps45.model.effects;

import it.polimi.ingsw.ps45.model.actions.state.CouncilPrivilegeOneState;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * An odd effect that makes the player exchange consumables for a council privilege.
 */
public class ProductionExchangeConsumableSetCouncilPrivilegeEffect implements Effect{
	
	private ConsumableSet before;
	
	/**
 	 * Constructor
 	 * @param before the ConsumableSet that the player has to "pay" for the exchange.
	 */
	public ProductionExchangeConsumableSetCouncilPrivilegeEffect(ConsumableSet before){
		this.before = before;
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
			p.getActionBuilder().setState(new CouncilPrivilegeOneState());
		}
		
	}

}
