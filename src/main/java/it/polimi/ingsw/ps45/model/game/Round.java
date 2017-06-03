package it.polimi.ingsw.ps45.model.game;

import it.polimi.ingsw.ps45.model.actions.state.NoActionState;
import it.polimi.ingsw.ps45.model.actions.state.PawnActionState;
import it.polimi.ingsw.ps45.model.player.Player;

public class Round {
	private Player[] turnOrder;
	int index;
	private boolean hasEnded;
	
	public Round(Player[] turnOrder){
		hasEnded = false;
		this.turnOrder = turnOrder;
		index = 0;

	}
	
	// Returns true if round has ended
	public void nextTurn(){
		turnOrder[index].getActionBuilder().setState(new NoActionState());
		
		if(index < turnOrder.length-1){
			index++;
			turnOrder[index].getActionBuilder().setState(new PawnActionState());
		}
		else{
			hasEnded = true;
		}
	}

	public Player getCurrentPlayer() {
		return turnOrder[index];
	}
	
	public boolean roundEnded(){
		return hasEnded;
	}

	
	
	
}
