package it.polimi.ingsw.ps45.model.area.cardarea;

import it.polimi.ingsw.ps45.model.area.Area;
import it.polimi.ingsw.ps45.model.cards.Character;

public abstract class CharacterCardArea extends Area{
	private Character c;

	public Character getCharacter() {
		return c;
	}

	public void setCharacter(Character c) {
		this.c = c;
	}
	
}
