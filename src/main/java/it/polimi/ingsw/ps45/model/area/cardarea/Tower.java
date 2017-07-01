package it.polimi.ingsw.ps45.model.area.cardarea;

import it.polimi.ingsw.ps45.model.player.Player;

public interface Tower {
	/**
	 * @return true if any of the areas in the tower is occupied.
	 */
	public boolean isOccupied();
	
	/**
	 * @param p the player that the area will be checked if is occupied by.
	 * @return true if any of the areas in the tower is occupied by the player in the parameters.
	 */
	public boolean isOccupiedByPlayer(Player p);
}
