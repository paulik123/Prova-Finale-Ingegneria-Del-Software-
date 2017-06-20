package it.polimi.ingsw.ps45.model.vatican;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.cards.Era;
import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.player.Player;

public class ExcommunicationCard implements Serializable{
	Era era;
	Effect effect;
	private String name;
	
	public ExcommunicationCard(Era era, Effect effect){
		this.era = era;
		this.effect = effect;
	}
	
	public void immediateEffect(Player p, int value){
		effect.runEffect(p, value);
	}
	
	public String getName(){
		return name;
	}

	public Era getEra() {
		return era;
	}
	
	
}
