package it.polimi.ingsw.ps45.model.area.cardarea;

import it.polimi.ingsw.ps45.model.area.Area;
import it.polimi.ingsw.ps45.model.cards.Building;

public abstract class BuildingCardArea extends Area{
	private Building b;

	public Building getBuilding() {
		return b;
	}

	public void setBuilding(Building b) {
		this.b = b;
	}
	
}
