package it.polimi.ingsw.ps45.model.area;

import it.polimi.ingsw.ps45.model.player.PawnType;
import it.polimi.ingsw.ps45.model.player.Player;

public class PlayerPawnPair {
	Player p;
	PawnType type;
	
	public PlayerPawnPair(Player p, PawnType type){
		this.p = p;
		this.type = type;
	}
}
