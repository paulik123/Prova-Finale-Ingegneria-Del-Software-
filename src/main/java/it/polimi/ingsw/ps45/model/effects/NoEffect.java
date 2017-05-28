package it.polimi.ingsw.ps45.model.effects;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.actions.state.NoActionState;
import it.polimi.ingsw.ps45.model.player.Player;

public class NoEffect implements Effect, Serializable{

	@Override
	public void runEffect(Player p, int value) {
		
	}

}
