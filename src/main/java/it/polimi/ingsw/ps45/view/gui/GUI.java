package it.polimi.ingsw.ps45.view.gui;

import it.polimi.ingsw.ps45.model.game.Game;
import it.polimi.ingsw.ps45.view.View;
import it.polimi.ingsw.ps45.view.gui.windowb.ControlBoard;
import it.polimi.ingsw.ps45.view.gui.windowb.GameBoard;
import it.polimi.ingsw.ps45.view.gui.windowb.PlayerBoard;

public class GUI extends View{
	
	private GameBoard gameBoard;
	
	private PlayerBoard playerBoard;
	
	private ControlBoard controlBoard;
	
	public GUI(Game game, String playerID){
		GameBoard gameBoard = new GameBoard(game);
		gameBoard.setVisible(true);
		
		PlayerBoard playerBoard = new PlayerBoard(game, playerID);
		playerBoard.setVisible(true);
		
		ControlBoard controlBoard = new ControlBoard(game, playerID);
		controlBoard.setVisible(true);
	}

	@Override
	public void updateView(String gameJSON) {
		// TODO Auto-generated method stub
		
	}

	public GameBoard getGameBoard() {
		return gameBoard;
	}

	public PlayerBoard getPlayerBoard() {
		return playerBoard;
	}

	public ControlBoard getControlBoard() {
		return controlBoard;
	}
	
	

}
