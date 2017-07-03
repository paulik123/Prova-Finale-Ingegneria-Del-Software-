package it.polimi.ingsw.ps45.model.effects;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.player.PawnType;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * An effect that increases or decreases the value of a player's colored pawns. It can be used as a penalty or as a bonus. 
 */
public class ColoredPawnPenaltyEffect implements Effect, Serializable{

	private int penalty;
	
	/**
 	 * Constructor
 	 * @param penalty the value of the change. Use negative numbers for penalties, positive for bonuses.
	 */
	public ColoredPawnPenaltyEffect(int penalty){
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
		p.getResourceSet().setModifierPawn(PawnType.BLACK, penalty + p.getResourceSet().getPawnModifier(PawnType.BLACK));
		p.getResourceSet().setModifierPawn(PawnType.WHITE, penalty + p.getResourceSet().getPawnModifier(PawnType.WHITE));
		p.getResourceSet().setModifierPawn(PawnType.ORANGE, penalty + p.getResourceSet().getPawnModifier(PawnType.ORANGE));
	}

}
