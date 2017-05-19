package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.actions.state.*;
import it.polimi.ingsw.ps45.model.area.Board;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

public class ActionBuilder {
	private ActionBuilderState state;
	private Board board;
	private Player p;
	
	
	public ActionBuilder(Player p, Board board){
		this.p = p;
		this.board = board;
		
		state = new NoActionState();
	}


	public ActionBuilderState getState() {
		return state;
	}


	public void setState(ActionBuilderState state) {
		this.state = state;
	}


	public Board getBoard() {
		return board;
	}


	public void setBoard(Board board) {
		this.board = board;
	}
	
	
	
	
	
}
