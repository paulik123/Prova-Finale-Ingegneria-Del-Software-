package it.polimi.ingsw.ps45.model.game;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.player.Player;

public class GameData {
	//private ArrayList<Player> players;
	private String status;
	
	
	
	public GameData(Game game){
		//this.players = game.getPlayers();
		this.status = game.getStatus();
	}






	public String getStatus() {
		return status;
	}
	
	
	
}
