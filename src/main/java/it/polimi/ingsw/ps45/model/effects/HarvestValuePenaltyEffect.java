package it.polimi.ingsw.ps45.model.effects;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

public class HarvestValuePenaltyEffect implements Effect, Serializable{

	int penalty;
	
	public HarvestValuePenaltyEffect(int penalty){
		this.penalty = penalty;
	}
	
	@Override
	public void runEffect(Player p, int value) {
		int h = p.getResourceSet().getActionValueModifier().getHarvest() + penalty;
		p.getResourceSet().getActionValueModifier().setHarvest(h);
	}

}
