package it.polimi.ingsw.ps45.model.effects;

import it.polimi.ingsw.ps45.model.player.Player;

/**
 * An effect that makes the player skip his first turn of the round.
 */
public class NoFirstTurnEffect implements Effect{

	/**
	 * The method that runs the effect.
	 * 
	 *@param p the player that runs the effect.
	 *@param value the value of the effect. It is needed only when nesting actions(an action makes you do another action with a certain value).
	 */
	@Override
	public void runEffect(Player p, int value) {
		p.getResourceSet().getPermanentEffects().setNoFirstTurn(true);
	}

}
