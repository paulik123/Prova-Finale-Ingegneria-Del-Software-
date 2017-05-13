package it.polimi.ingsw.ps45.model.cards;

import it.polimi.ingsw.ps45.model.player.Player;

public abstract class Territory extends Card {
	int level;
	abstract public void harvest(Player p);
}
