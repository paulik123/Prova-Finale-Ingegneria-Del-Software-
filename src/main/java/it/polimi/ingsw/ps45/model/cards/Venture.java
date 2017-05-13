package it.polimi.ingsw.ps45.model.cards;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

public abstract class Venture extends Card {
	abstract public void endGameEffect(Player p);
	abstract public boolean hasAlternativeCost();
	abstract public ConsumableSet alternativeCost();
}
