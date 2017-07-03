package it.polimi.ingsw.ps45.model.effects;

import it.polimi.ingsw.ps45.model.player.Player;

/**
 * An effect that makes the player's colored pawns have a default value. 
 */
public class NeutralPawnDefaultValue implements Effect{
	
	private int value;
	
	/**
 	 * Constructor
 	 * @param val The value that will be added to the player's neutral pawn default value.
	 */
	public NeutralPawnDefaultValue(int value){
		this.value = value;
	}

	/**
	 * The method that runs the effect.
	 * 
	 *@param p the player that runs the effect.
	 *@param value the value of the effect. It is needed only when nesting actions(an action makes you do another action with a certain value).
	 */
	@Override
	public void runEffect(Player p, int value) {
		int oldValue = p.getResourceSet().getPawnSet().getDefaultNeutralValue();
		p.getResourceSet().getPawnSet().setDefaultNeutralValue(oldValue + value);
	}

}
