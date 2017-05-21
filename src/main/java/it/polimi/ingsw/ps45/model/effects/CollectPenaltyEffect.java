package it.polimi.ingsw.ps45.model.effects;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

public class CollectPenaltyEffect implements Effect, Serializable{

	ConsumableSet penalty;
	
	public CollectPenaltyEffect(ConsumableSet penalty){
		this.penalty = penalty;
	}
	
	@Override
	public void runEffect(Player p, int value) {
		p.getResourceSet().getCollectPenalty().collect(penalty);
	}

}
