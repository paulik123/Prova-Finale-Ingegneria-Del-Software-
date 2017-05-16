package it.polimi.ingsw.ps45.model.area.cardarea;

import it.polimi.ingsw.ps45.model.area.Area;
import it.polimi.ingsw.ps45.model.cards.Venture;

public abstract class VentureCardArea extends Area{
	private Venture v;

	public Venture getVenture() {
		return v;
	}

	public void setVenture(Venture v) {
		this.v = v;
	}
	
}
