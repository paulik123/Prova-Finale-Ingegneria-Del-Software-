package it.polimi.ingsw.ps45.model.cards;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;

public abstract class Venture extends Card {
	public Venture(Era e) {
		super(e);
		// TODO Auto-generated constructor stub
	}
	abstract public ConsumableSet endGameVictoryPoints();
	abstract public ConsumableSet costI();
	abstract public ConsumableSet costII();
	abstract public ConsumableSet requirementsI();
	abstract public ConsumableSet requirementsII();
	
}
