package it.polimi.ingsw.ps45.model.cards;

import it.polimi.ingsw.ps45.model.player.Player;

public abstract class Building {
	int level;
	abstract public void production(Player p, int mode);
}
