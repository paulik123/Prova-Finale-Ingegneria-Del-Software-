package it.polimi.ingsw.ps45.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.polimi.ingsw.ps45.gson.PropertyBasedInterfaceMarshal;
import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.game.Game;

public class GameCreator {
	private ArrayList<Game> games;
	private Game pendingGame;
	private HashMap<String, Game> playersInGames;
	
	private static final int MAX_NUM_OF_PLAYERS = 4;
	
	public GameCreator(){
		games = new ArrayList<Game>();
		playersInGames = new HashMap<String, Game>();
		pendingGame = new Game();
	}
	
	public void addPlayer(String ID, Observer o) throws Exception{
		if(playerExists(ID)) throw new Exception("Player already exists");
		pendingGame.addPlayer(ID);
		pendingGame.registerObserver(o);
		System.out.println("SERVER: added player: "+ ID);
		if(pendingGame.getNumberOfPlayers() == MAX_NUM_OF_PLAYERS){
			games.add(pendingGame);
			pendingGame.start();
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
