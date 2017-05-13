package it.polimi.ingsw.ps45.model.cards;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

public abstract class Card {
	protected Era era;
	
	abstract public ConsumableSet cost();
	abstract public void immediateEffect(Player p);
	
	abstract public Era getEra();
	
}
