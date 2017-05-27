package it.polimi.ingsw.ps45.model.game;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.area.Board;
import it.polimi.ingsw.ps45.model.player.Player;

public class GameData {
	private ArrayList<Player> players;
	private String status;
	private int currentEra;
	private int roundNumber;
	private Board board;
	
	
	
	public GameData(Game game){
		//this.players = game.getPlayers();
		this.status = game.getStatus();
		this.currentEra = currentEra;
		this.roundNumber = roundNumber;
		this.board = board;
	}






	public String getStatus() {
		return status;
	}
	
	
	
}
