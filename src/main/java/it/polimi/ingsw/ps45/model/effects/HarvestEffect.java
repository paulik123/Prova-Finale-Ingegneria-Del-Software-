package it.polimi.ingsw.ps45.model.effects;

import it.polimi.ingsw.ps45.model.actions.state.HarvestState;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * An effect that sets the player's action builder state to HarvestState so he can make a harvest.
 */
public class HarvestEffect implements Effect{

	private int penalty;
	
	/**
 	 * Constructor
 	 * @param penalty The value of the penalty of the value the harvest will have. Used by BigHarvest are for example to have a value of -3.
	 */
	public HarvestEffect(int penalty){
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
		System.out.println("HarvestEffect: " + value);
		p.getActionBuilder().setState(new HarvestState(value - penalty));
		p.setStatus("Make a harvest.");
	}

}
