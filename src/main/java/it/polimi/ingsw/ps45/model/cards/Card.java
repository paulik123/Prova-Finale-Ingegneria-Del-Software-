package it.polimi.ingsw.ps45.model.cards;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

public abstract class Card {
	private Era era;
	
	abstract public void immediateEffect(Player p);
	
	public Card(Era e){
		this.era = e;
	}
	
}
