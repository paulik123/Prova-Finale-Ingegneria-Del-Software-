package it.polimi.ingsw.ps45.model.effects;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.player.Player;

/**
 * An effect that makes reduces the player's end game victory points depending on how much his buildings cost(wood/stone). Used in an excom card. 
 */
public class BuildingCostVictoryPointsEffect implements Effect, Serializable{

	/**
	 * The method that runs the effect.
	 * 
	 *@param p the player that runs the effect.
	 *@param value the value of the effect. It is needed only when nesting actions(an action makes you do another action with a certain value).
	 */
	@Override
	public void runEffect(Player p, int value) {
		p.getResourceSet().getPermanentEffects().setBuildingCostVictoryPointsPenalty(true);
	}

}
