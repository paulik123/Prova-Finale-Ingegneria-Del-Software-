package it.polimi.ingsw.ps45.model.game;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.actions.state.NoActionState;
import it.polimi.ingsw.ps45.model.actions.state.PawnActionState;
import it.polimi.ingsw.ps45.model.cards.Era;
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
		if(index >= turnOrder.length){
			hasEnded = true;
			return;
		}
		
		turnOrder[index].getActionBuilder().setState(new NoActionState());
		index++;
		if(index < turnOrder.length)turnOrder[index].getActionBuilder().setState(new PawnActionState());
	}

	public Player getCurrentPlayer() {
		return turnOrder[index];
	}
	
	public boolean roundEnded(){
		return hasEnded;
	}

	
	
	
}
