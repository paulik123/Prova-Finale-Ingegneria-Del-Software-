package it.polimi.ingsw.ps45.model.area.cardarea;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.area.Area;
import it.polimi.ingsw.ps45.model.cards.Character;
import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * An area that contains character cards.
 */
public class CharacterCardArea extends Area{
	private Effect effect;
	private Character c;
	
	/**
 	 * Constructor
 	 * @param cost the (pawn)cost of the area. 
 	 * @param maxOccupants maximum number of the occupants that the area can hold.
 	 * @param effect The effect that the area will have.
 	 * @param name the name of the area.
	 */
	public CharacterCardArea(int cost, Effect effect, String name){
		super();
		super.setName(name);
		this.setCost(cost);
		this.effect = effect;
		this.maxOccupants = 1;
	}
	
	
	/**
	 * @return The character card of the area.
	 */
	public Character getCharacter() {
		return c;
	}

	/**
	 * Sets the character of the area.
	 * @param c The character to be added to the area.
	 */
	public void setCharacter(Character c) {
		this.c = c;
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
