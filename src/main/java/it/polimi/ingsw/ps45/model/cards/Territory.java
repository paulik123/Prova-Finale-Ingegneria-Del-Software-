package it.polimi.ingsw.ps45.model.cards;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

public abstract class Territory extends Card {
	public Territory(Era e, int harvestLevel) {
		super(e);
		this.harvestLevel = harvestLevel;
	}

	private int harvestLevel;
	abstract public void harvest(Player p);
	
	public int getHarvestLevel(){
		return harvestLevel;
	}
}
