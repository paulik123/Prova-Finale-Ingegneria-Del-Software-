package it.polimi.ingsw.ps45.model.effects;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

public class ConsumableSetPerMilitaryPointsEffect implements Effect, Serializable {

	ConsumableSet cs;
	
	public ConsumableSetPerMilitaryPointsEffect(ConsumableSet cs){
		this.cs = cs;
	}
	
	
	@Override
	public void runEffect(Player p, int value) {
		int militaryPoints = p.getResourceSet().getResources().getMilitaryPoins();
		
		ConsumableSet reward = new ConsumableSet();
		
		for(int i=0;i<militaryPoints/2;i++){
			reward.collect(cs);
		}
		
		p.getResourceSet().collect(reward);
		
	}
	
}
