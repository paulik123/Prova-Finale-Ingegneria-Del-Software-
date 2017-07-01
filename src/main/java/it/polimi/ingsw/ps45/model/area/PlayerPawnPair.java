package it.polimi.ingsw.ps45.model.area;

import it.polimi.ingsw.ps45.model.player.PawnType;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * An class that defines a pair formed by a pawn and the player that owns it. It is used to define occupants of areas.
 */
public class PlayerPawnPair {
	private Player p;
	private PawnType type;
	
	/**
 	 * Constructor
 	 * @param p the player of the pair. 
 	 * @param type the type of the pawn.
	 */
	public PlayerPawnPair(Player p, PawnType type){
		this.p = p;
		this.type = type;
	}

	/**
	 * @return The player in the pair.
	 */
	public Player getPlayer() {
		return p;
	}

	/**
	 * @return The type of the pawn in the pair.
	 */
	public PawnType getType() {
		return type;
	}
}
