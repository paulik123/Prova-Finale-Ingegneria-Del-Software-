package it.polimi.ingsw.ps45.model.effects;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

public class ConsumableSetPerTerritoryEffect implements Effect, Serializable {

	ConsumableSet cs;
	
	public ConsumableSetPerTerritoryEffect(ConsumableSet cs){
		this.cs = cs;
	}
	
	
	@Override
	public void runEffect(Player p, int value) {
		int times = p.getResourceSet().getTerritoryList().size();
		
		ConsumableSet reward = new ConsumableSet();
		
		for(int i=0;i<times;i++){
			reward.collect(cs);
		}
		
		p.getResourceSet().collect(reward);
		
	}
	
}
