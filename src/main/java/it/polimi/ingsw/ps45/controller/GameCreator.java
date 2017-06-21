package it.polimi.ingsw.ps45.controller;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps45.model.game.Game;
import it.polimi.ingsw.ps45.model.game.Observer;

public class GameCreator {
	private ArrayList<Game> games;
	private Game pendingGame;
	private HashMap<String, Game> playersInGames;
	

	
	public GameCreator(){
		games = new ArrayList<Game>();
		playersInGames = new HashMap<String, Game>();
		pendingGame = new Game();
	}
	
	public void addPlayer(String ID, String bonusTile, Observer o) throws Exception{
		if(playerExists(ID)) throw new Exception("Player already exists");
		pendingGame.addPlayer(ID, bonusTile, o);
		playersInGames.put(ID, pendingGame);
		
		
		if(pendingGame.hasStarted()){
			games.add(pendingGame);
			pendingGame = new Game();
		}
	}
	
	private boolean playerExists(String ID){
		for(String s:playersInGames.keySet()){
			if(s.equals(ID)) return true;
		}
		return false;
	}
	
	public Game getGameFromPlayerID(String ID) throws Exception{
		if(!playerExists(ID)) throw new Exception("Player does not exist");
		return playersInGames.get(ID);
	}
}
