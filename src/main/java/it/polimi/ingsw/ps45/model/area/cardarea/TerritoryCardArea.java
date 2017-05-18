package it.polimi.ingsw.ps45.model.area.cardarea;

import it.polimi.ingsw.ps45.model.area.Area;
import it.polimi.ingsw.ps45.model.cards.Territory;

public abstract class TerritoryCardArea extends Area{
	private Territory t;

	public Territory getTerritory() {
		return t;
	}

	public void setTerritory(Territory t) {
		this.t = t;
	}
	
}
