package it.polimi.ingsw.ps45.model.cards;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.player.Player;

public abstract class Card implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7839605187878687863L;
	private Era era;
	private ArrayList<Effect> effects;
	private String name;

	abstract public void immediateEffect(Player p);
	
	public Card(Era e, String name){
		this.era = e;
		this.name = name;
		effects = new ArrayList<Effect>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public void addEffect(Effect effect) {
		effects.add(effect);
	}
	
	public ArrayList<Effect> getEffects(){
		return effects;
	}

	public Era getEra() {
		return era;
	}
	
	
	
}
