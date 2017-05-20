package it.polimi.ingsw.ps45.model.game;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.actions.state.PawnActionState;
import it.polimi.ingsw.ps45.model.cards.Era;
import it.polimi.ingsw.ps45.model.player.Player;

public class Round {
	private Player[] turnOrder;
	int index;
	
	public Round(Player[] turnOrder){
		this.turnOrder = turnOrder;
		index = 0;
	}
	
	// Returns true if round has ended
	public boolean nextTurn(){
		if(index >= turnOrder.length){
			return true;
		}
		turnOrder[index].getActionBuilder().setState(new PawnActionState());
		index++;
		return false;
	}

	public Player getCurrentPlayer() {
		return turnOrder[index];
	}

	
	
	
}
