package it.polimi.ingsw.ps45.model.effects;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.actions.state.HarvestState;
import it.polimi.ingsw.ps45.model.player.Player;

public class HarvestEffect implements Effect, Serializable{

	int penalty;
	
	public HarvestEffect(int penalty){
		this.penalty = penalty;
	}
	@Override
	public void runEffect(Player p, int value) {
		p.getActionBuilder().setState(new HarvestState(value - penalty));
	}

}
