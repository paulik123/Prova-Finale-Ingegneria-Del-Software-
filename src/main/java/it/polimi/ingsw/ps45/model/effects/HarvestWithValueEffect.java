package it.polimi.ingsw.ps45.model.effects;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.actions.state.AddServantsToHarvestState;
import it.polimi.ingsw.ps45.model.player.Player;

public class HarvestWithValueEffect implements Effect, Serializable{


	private static final long serialVersionUID = -4877189015913198210L;
	int val;
	
	public HarvestWithValueEffect(int val){
		this.val = val;
	}
	@Override
	public void runEffect(Player p, int value) {
		p.getActionBuilder().setState(new AddServantsToHarvestState(val));
	}

}
