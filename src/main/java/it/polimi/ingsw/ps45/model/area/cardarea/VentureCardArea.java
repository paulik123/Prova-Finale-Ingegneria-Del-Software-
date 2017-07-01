package it.polimi.ingsw.ps45.model.area.cardarea;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.area.Area;
import it.polimi.ingsw.ps45.model.cards.Venture;
import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * An area that contains territory cards.
 */
public class VentureCardArea extends Area implements Serializable{
	private Effect effect;
	private Venture v;
	
	/**
 	 * Constructor
 	 * @param cost the (pawn)cost of the area. 
 	 * @param maxOccupants maximum number of the occupants that the area can hold.
 	 * @param effect The effect that the area will have.
 	 * @param name the name of the area.
	 */
	public VentureCardArea(int cost, Effect effect, String name){
		super();
		this.setName(name);
		this.setCost(cost);
		this.effect = effect;
		this.maxOccupants = 1;
	}
	
	
	/**
	 * @return The territory card of the area.
	 */
	public Venture getVenture() {
		return v;
	}

	/**
	 * Sets the venture of the area.
	 * @param b The venture to be added to the area.
	 */
	public void setVenture(Venture v) {
		this.v = v;
	}

	/**
	 * Applies the effects to player parameter
	 * @param p The effect that will be applied.
	 * @param value of the effect that will be applied.
	 */
	@Override
	public void immediateEffect(Player p, int value) {
		effect.runEffect(p, value);
	}
	
}
