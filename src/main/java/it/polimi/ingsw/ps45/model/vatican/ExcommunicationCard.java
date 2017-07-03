package it.polimi.ingsw.ps45.model.vatican;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.cards.Era;
import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * Excommunicaton Card. It's effects are applied to the player if he doesn't have enought faith points at the end of an era
 * or he refuses vatican.
 */ 
public class ExcommunicationCard{
	private Era era;
	private Effect effect;
	private String name;
	
	/**
 	 * Constructor
	 * @param era the era this card belongs to.
	 * @param effect the effect that will be applied if the player refuses vatican.
	 * @param name the name of the card
	 */
	public ExcommunicationCard(Era era, Effect effect, String name){
		this.era = era;
		this.effect = effect;
		this.name = name;
	}
	
	/**
	 * Applies the immediate effect of the card to the player
	 * @param p The effect that will be applied.
	 */
	public void immediateEffect(Player p, int value){
		effect.runEffect(p, value);
	}
	
	/**
	 * @return the name of the card.
	 */
	public String getName(){
		return name;
	}

	/**
	 * @return the era this card belongs to.
	 */
	public Era getEra() {
		return era;
	}
}
