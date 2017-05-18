package it.polimi.ingsw.ps45.model.area.cardarea;

import it.polimi.ingsw.ps45.model.player.Player;

public interface Tower {
	public boolean isOccupied();
	public boolean isOccupiedByPlayer(Player p);
}
