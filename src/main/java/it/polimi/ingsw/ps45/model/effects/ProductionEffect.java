package it.polimi.ingsw.ps45.model.effects;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.actions.state.ProductionState;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * An effect that sets the player's action builder state to ProductionState so he can make a production.
 */
public class ProductionEffect implements Effect{
	private int penalty;
	
	
	/**
 	 * Constructor
 	 * @param penalty The value of the penalty of the value the production will have. Used by BigProduction are for example to have a value of -3.
	 */
	public ProductionEffect(int penalty){
		this.penalty = penalty;
	}

	/**
	 * The method that runs the effect.
	 * 
	 *@param p the player that runs the effect.
	 *@param value the value of the effect. It is needed only when nesting actions(an action makes you do another action with a certain value).
	 */
	@Override
	public void runEffect(Player p, int value) {
		p.getActionBuilder().setState(new ProductionState(value - penalty));
		p.setStatus("Make a production");
	}
}
