package it.polimi.ingsw.ps45.model.cards;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * Abstract card that defines all the basic functions of a card.
 */

public abstract class Card implements Serializable {

	private static final long serialVersionUID = -7839605187878687863L;
	private Era era;
	private ArrayList<Effect> effects;
	private String name;

	/**
	 * Applies the immediate effect of the card to the player
	 * @param p The effect that will be applied.
	 */
	public void immediateEffect(Player p){
		for(Effect e:effects){
			e.runEffect(p, 0);
		}
	}
	
	/**
 	 * Constructor
 	 * @param e The era in which the card belongs to.
 	 * @param name The name of the card.
	 */
	public Card(Era e, String name){
		this.era = e;
		this.name = name;
		effects = new ArrayList<Effect>();
	}
	
	/**
	 * @return The name of the card.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the card.
	 * @param the name to be set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Adds an effect to the immediateEffect list.
	 * @param the effect to be added.
	 */
	public void addEffect(Effect effect) {
		effects.add(effect);
	}
	
	/**
	 * @return A list of all immediateEffects.
	 */
	public ArrayList<Effect> getEffects(){
		return effects;
	}

	/**
	 * @return The era in which the card belongs to.
	 */
	public Era getEra() {
		return era;
	}
	
	
	
}
