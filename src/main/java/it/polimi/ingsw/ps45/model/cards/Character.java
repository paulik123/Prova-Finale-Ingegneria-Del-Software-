package it.polimi.ingsw.ps45.model.cards;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;

public abstract class Character extends Card {
	public Character(Era e) {
		super(e);
		// TODO Auto-generated constructor stub
	}

	public abstract ConsumableSet cost();
}
