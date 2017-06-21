package it.polimi.ingsw.ps45.model.effects;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.actions.state.NoActionState;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

public class AreaCollectEffect implements Effect, Serializable{
	ConsumableSet cs;
	
	public AreaCollectEffect(ConsumableSet cs){
		this.cs = cs;
	}
	@Override
	public void runEffect(Player p, int value) {
		p.getResourceSet().collect(cs);
	}
}
