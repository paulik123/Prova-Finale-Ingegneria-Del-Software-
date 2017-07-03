package it.polimi.ingsw.ps45.model.effects;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.actions.state.TakeCardState;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * An effect that sets the player's action builder state to TakeCardState so he can make a take any card action.
 */
public class TakeAnyCardEffect implements Effect, Serializable{


	private int val;
	private ConsumableSet discount;
	
	public TakeAnyCardEffect(int val, ConsumableSet discount){
		this.val = val;
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
		p.getActionBuilder().setState(new TakeCardState(val, discount));
		p.setStatus("Take any card. Value: " + val);
	}

}
