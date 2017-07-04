package it.polimi.ingsw.ps45.model.effects;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * An effect that adds a penalty whenever the player collects consumables. For example: get -1 wood.
 */
public class CollectPenaltyEffect implements Effect{

	private ConsumableSet penalty;
	
	/**
 	 * Constructor
 	 * @param cs The ConsumableSet that represents the penalty. So for example if penalty contains 1 wood the player will receive -1 wood whenever he collects.
	 */
	public CollectPenaltyEffect(ConsumableSet penalty){
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
		p.getResourceSet().getCollectPenalty().collect(penalty);
	}

}
