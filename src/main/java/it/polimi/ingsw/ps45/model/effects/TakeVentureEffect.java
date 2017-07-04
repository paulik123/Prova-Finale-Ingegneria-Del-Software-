package it.polimi.ingsw.ps45.model.effects;

import it.polimi.ingsw.ps45.model.actions.state.TakeVentureState;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * An effect that sets the player's action builder state to TakeCharacterState so he can make a take venture action.
 */
public class TakeVentureEffect implements Effect{


	private int val;
	private ConsumableSet discount;
	
	/**
 	 * Constructor
 	 * @param effectValue The value of the action.
 	 * @param discount a ConsumableSet that represents the discount a player will have when acquiring the venture.
	 */
	public TakeVentureEffect(int val, ConsumableSet discount){
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
		p.getActionBuilder().setState(new TakeVentureState(val, discount));
		p.setStatus("Take any venture. Value: " + val);
	}

}
