package it.polimi.ingsw.ps45.game;

import java.util.HashMap;

import it.polimi.ingsw.ps45.model.area.Board;
import it.polimi.ingsw.ps45.model.cards.Era;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

public class Game {
	
	private int numberOfPlayers;
	public String gameID;
	
	private Era currentEra;
	private HashMap<String, Player> players;
	private Board board;
	
	public Game(String gameID, int numberOfPlayers){
		this.gameID = gameID;
		this.numberOfPlayers = numberOfPlayers;
		
		currentEra = Era.I;
		players = new HashMap<String, Player>();
		board = new Board();
	}
	
	public void addPlayer(String playerID) throws Exception{
		if(players.containsKey(playerID)) throw new Exception("PlayerID already exists");
		if(players.size() >= numberOfPlayers) throw new Exception("Game is full");
		
		ConsumableSet cs = new ConsumableSet();
		cs.setWood(Player.defaultWood);
		cs.setStone(Player.defaultStone);
		cs.setServants(Player.defaultServants);
		cs.setCoins(Player.defaultCoins + players.size());
		
		Player p = new Player(board, cs);
		players.put(playerID, p);
	}
}
