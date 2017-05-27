package it.polimi.ingsw.ps45.model.area.cardarea;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.area.Area;
import it.polimi.ingsw.ps45.model.cards.Venture;
import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.player.Player;

public class VentureCardArea extends Area implements Serializable{
	Effect effect;
	
	public VentureCardArea(int cost, Effect effect, String name){
		super();
		this.setName(name);
		this.setCost(cost);
		this.effect = effect;
		this.maxOccupants = 1;
	}
	
	private Venture v;

	public Venture getVenture() {
		return v;
	}

	public void setVenture(Venture v) {
		this.v = v;
	}

	@Override
	public void immediateEffect(Player p, int value) {
		effect.runEffect(p, value);
	}
	
}
