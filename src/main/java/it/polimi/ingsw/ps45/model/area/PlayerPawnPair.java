package it.polimi.ingsw.ps45.model.area;

import it.polimi.ingsw.ps45.model.player.PawnType;
import it.polimi.ingsw.ps45.model.player.Player;

public class PlayerPawnPair {
	private Player p;
	private PawnType type;
	
	public PlayerPawnPair(Player p, PawnType type){
		this.p = p;
		this.type = type;
	}

	public Player getPlayer() {
		return p;
	}

	public PawnType getType() {
		return type;
	}
}
