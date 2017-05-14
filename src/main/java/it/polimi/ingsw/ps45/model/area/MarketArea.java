package it.polimi.ingsw.ps45.model.area;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

public class MarketArea extends Area{
ConsumableSet immediateEffectCS;
	
	public MarketArea(ConsumableSet cs, int cost){
		
		occupiedBy = new ArrayList<Player>();
		maxOccupants = 1;
		
		this.immediateEffectCS = cs;
		this.setCost(cost);
	}

	public void immediateEffect(Player p) {
		p.getResourceSet().collect(immediateEffectCS);
	}
}
