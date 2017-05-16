package it.polimi.ingsw.ps45.model.cards;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

public abstract class Building extends Card {
	public Building(Era e, int productionLevel) {
		super(e);
		this.productionLevel = productionLevel;
	}
	abstract ConsumableSet cost();
	abstract public void productionI(Player p);
	abstract public void productionII(Player p);
	
	private int productionLevel;
	public int getProductionLevel(){
		return productionLevel;
	}
}
