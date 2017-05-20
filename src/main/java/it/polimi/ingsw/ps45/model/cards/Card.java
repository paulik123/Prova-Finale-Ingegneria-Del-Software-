package it.polimi.ingsw.ps45.model.cards;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.player.Player;

public abstract class Card implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2157937128089424956L;

	private Era era;
	private Effect effect;
	private String name;

	abstract public void immediateEffect(Player p);
	
	public Card(Era e, String name, Effect effect){
		this.era = e;
		this.name = name;
		this.effect = effect;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Effect getEffect() {
		return effect;
	}

	public void setEffect(Effect effect) {
		this.effect = effect;
	}

	public Era getEra() {
		return era;
	}
	
	
	
}
