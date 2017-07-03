package it.polimi.ingsw.ps45.model.effects;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.player.Player;

/**
 * An effect that sets a penalty in the value of the harvests the player will make.
 */
public class HarvestValuePenaltyEffect implements Effect, Serializable{

	private int penalty;
	
	/**
 	 * Constructor
 	 * @param penalty The value of the penalty the player will suffer from every time he tries to do a harvest.
	 */
	public HarvestValuePenaltyEffect(int penalty){
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
		int h = p.getResourceSet().getActionValueModifier().getHarvest() + penalty;
		p.getResourceSet().getActionValueModifier().setHarvest(h);
	}

}
