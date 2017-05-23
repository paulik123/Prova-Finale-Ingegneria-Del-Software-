package it.polimi.ingsw.ps45.model.effects;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.player.Player;

public class ProductionValuePenaltyEffect implements Effect, Serializable{

	int penalty;
	
	public ProductionValuePenaltyEffect(int penalty){
		this.penalty = penalty;
	}
	
	@Override
	public void runEffect(Player p, int value) {
		int h = p.getResourceSet().getActionValueModifier().getProduction() + penalty;
		p.getResourceSet().getActionValueModifier().setProduction(h);
	}

}
