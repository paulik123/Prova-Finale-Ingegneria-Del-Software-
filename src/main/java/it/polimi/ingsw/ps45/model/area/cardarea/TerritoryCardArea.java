package it.polimi.ingsw.ps45.model.area.cardarea;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.area.Area;
import it.polimi.ingsw.ps45.model.cards.Territory;
import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.player.Player;

public class TerritoryCardArea extends Area implements Serializable{
	private Territory t;
	Effect effect;
	
	public TerritoryCardArea(int cost, Effect effect){
		this.setCost(cost);
		this.effect = effect;
		this.maxOccupants = 1;
	}

	public Territory getTerritory() {
		return t;
	}

	public void setTerritory(Territory t) {
		this.t = t;
	}

	@Override
	public void immediateEffect(Player p, int value) {
		effect.runEffect(p, value);	
	}
	
}
