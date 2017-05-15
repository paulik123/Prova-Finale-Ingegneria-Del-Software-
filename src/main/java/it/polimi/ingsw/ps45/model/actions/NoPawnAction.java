package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;

public abstract class NoPawnAction extends Action {
	
	public ConsumableSet getCost(){
		return cost;
	}
	protected ConsumableSet cost;
	abstract protected void calculateCost();
}
