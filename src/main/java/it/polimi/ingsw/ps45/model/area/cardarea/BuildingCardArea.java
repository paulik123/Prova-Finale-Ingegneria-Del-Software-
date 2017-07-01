package it.polimi.ingsw.ps45.model.area.cardarea;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.area.Area;
import it.polimi.ingsw.ps45.model.cards.Building;
import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * An area that contains building cards.
 */
public class BuildingCardArea extends Area implements Serializable{
	private Building b;
	private Effect effect;
	
	/**
 	 * Constructor
 	 * @param cost the (pawn)cost of the area. 
 	 * @param maxOccupants maximum number of the occupants that the area can hold.
 	 * @param effect The effect that the area will have.
 	 * @param name the name of the area.
	 */
	public BuildingCardArea(int cost, Effect effect, String name){
		super();
		super.setName(name);
		this.setCost(cost);
		this.effect = effect;
		this.maxOccupants = 1;
		
	}
	
	/**
	 * @return The building card of the area.
	 */
	public Building getBuilding() {
		return b;
	}

	/**
	 * Sets the building of the area.
	 * @param b The building to be added to the area.
	 */
	public void setBuilding(Building b) {
		this.b = b;
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
