package it.polimi.ingsw.ps45.model.effects;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.player.Player;

/**
 * An effect that increases or decreases the player's action value when taking territories.
 */
public class TakeTerritoryActionModifierEffect implements Effect, Serializable{

	private int effectValue;
	
	/**
 	 * Constructor
 	 * @param effectValue The value of the action value modifier.
	 */
	public TakeTerritoryActionModifierEffect(int effectValue){
		this.effectValue = effectValue;
	}
	
	/**
	 * The method that runs the effect.
	 * 
	 *@param p the player that runs the effect.
	 *@param value the value of the effect. It is needed only when nesting actions(an action makes you do another action with a certain value).
	 */
	@Override
	public void runEffect(Player p, int value) {
		int h = p.getResourceSet().getActionValueModifier().getTerritoryAction() + effectValue;
		p.getResourceSet().getActionValueModifier().setTerritoryAction(h);
	}

}
