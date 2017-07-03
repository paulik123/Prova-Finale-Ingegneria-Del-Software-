package it.polimi.ingsw.ps45.model.effects;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.actions.state.AddServantsToHarvestState;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * An effect that sets the player's action builder state to AddServantsToHarvestState so he can make a harvest and also add servants to it's value.
 */
public class HarvestWithValueEffect implements Effect{

	private int val;
	
	/**
 	 * Constructor
 	 * @param val The value of the servants that will be added to the action.
	 */
	public HarvestWithValueEffect(int val){
		this.val = val;
	}
	
	/**
	 * The method that runs the effect.
	 * 
	 *@param p the player that runs the effect.
	 *@param value the value of the effect. It is needed only when nesting actions(an action makes you do another action with a certain value).
	 */
	@Override
	public void runEffect(Player p, int value) {
		p.getActionBuilder().setState(new AddServantsToHarvestState(val));
	}

}
