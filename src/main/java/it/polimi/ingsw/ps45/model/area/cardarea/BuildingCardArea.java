package it.polimi.ingsw.ps45.model.area.cardarea;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.area.Area;
import it.polimi.ingsw.ps45.model.cards.Building;
import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.player.Player;

public class BuildingCardArea extends Area implements Serializable{
	private Building b;
	Effect effect;
	
	public BuildingCardArea(int cost, Effect effect){
		this.setCost(cost);
		this.effect = effect;
		this.maxOccupants = 1;
	}
	

	public Building getBuilding() {
		return b;
	}

	public void setBuilding(Building b) {
		this.b = b;
	}

	@Override
	public void immediateEffect(Player p, int value) {
		effect.runEffect(p, value);
	}
	
}
