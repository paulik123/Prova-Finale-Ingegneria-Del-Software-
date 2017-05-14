package it.polimi.ingsw.ps45.model.cards;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

public abstract class Venture extends Card {
	abstract public void endGameEffect(Player p);
	private boolean hasNestedAction;
	
	private VentureMode mode;
	public VentureMode getMode() {
		return mode;
	}
	public void setMode(VentureMode mode) {
		this.mode = mode;
	}
	
}
