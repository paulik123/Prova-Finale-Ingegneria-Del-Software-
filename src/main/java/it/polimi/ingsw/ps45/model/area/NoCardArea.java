package it.polimi.ingsw.ps45.model.area;

import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * A simple area that doesn't contain any cards.
 */
public class NoCardArea extends Area{
	private Effect immediateEffect;

	/**
 	 * Constructor
 	 * @param cost the (pawn)cost of the area. 
 	 * @param maxOccupants maximum number of the occupants that the area can hold.
 	 * @param effect The effect that the area will have.
 	 * @param name the name of the area.
	 */
	public NoCardArea(int cost, int maxOccupants, Effect effect, String name){
		super();
		this.setCost(cost);
		this.maxOccupants = maxOccupants;
		this.immediateEffect = effect;
		super.setName(name);
	}

	/**
	 * Applies the effects to player parameter
	 * @param p The effect that will be applied.
	 * @param value of the effect that will be applied.
	 */
	@Override
	public void immediateEffect(Player p, int value) {
		immediateEffect.runEffect(p, value);
	}

}
