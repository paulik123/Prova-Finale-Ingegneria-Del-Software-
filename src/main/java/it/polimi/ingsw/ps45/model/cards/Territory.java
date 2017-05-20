package it.polimi.ingsw.ps45.model.cards;

import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.player.Player;

public class Territory extends Card {
	
	Effect harvestEffect;
	private int harvestLevel;
	
	
	public Territory(Era e, String name, Effect effect, Effect harvestEffect, int harvestLevel) {
		super(e, name, effect);
		this.harvestLevel = harvestLevel;
		this.harvestEffect = harvestEffect;
	}

	public void harvest(Player p){
		harvestEffect.runEffect(p, 0);
	}
	
	public int getHarvestLevel(){
		return harvestLevel;
	}

	@Override
	public void immediateEffect(Player p) {
		super.getEffect().runEffect(p, 0);
	}
}
