package it.polimi.ingsw.ps45.model.effects;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.player.Player;

public class NoCharacterVictoryPointsEffect implements Effect, Serializable{

	@Override
	public void runEffect(Player p, int value) {
		p.getResourceSet().getPermanentEffects().setNoCharacterVictoryPoints(true);
		
	}

}
