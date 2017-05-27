package it.polimi.ingsw.ps45.model.effects;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.actions.state.TakeBuildingState;
import it.polimi.ingsw.ps45.model.actions.state.TakeCardState;
import it.polimi.ingsw.ps45.model.actions.state.TakeCharacterState;
import it.polimi.ingsw.ps45.model.actions.state.TakeTerritoryState;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

public class TakeTerritoryEffect implements Effect, Serializable{


	int val;
	ConsumableSet discount;
	
	public TakeTerritoryEffect(int val, ConsumableSet discount){
		this.val = val;
		this.discount = discount;
	}
	
	@Override
	public void runEffect(Player p, int value) {
		p.getActionBuilder().setState(new TakeTerritoryState(val, discount));
		
	}

}