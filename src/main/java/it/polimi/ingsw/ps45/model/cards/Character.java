package it.polimi.ingsw.ps45.model.cards;

import it.polimi.ingsw.ps45.model.player.Player;

public abstract class Character extends Card {
	abstract public void applyEffect(Player p);
}
