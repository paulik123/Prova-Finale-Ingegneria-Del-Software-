package it.polimi.ingsw.ps45.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import it.polimi.ingsw.ps45.model.game.Game;
import it.polimi.ingsw.ps45.model.game.Observer;

public class GameCreator {
	private ArrayList<Game> games;
	private Game pendingGame;
	private HashMap<String, Game> playersInGames;
	private HashMap<Observer, Game> observersInGames;
	private Timer timer;
	private GameCreatorTimerTask timerTask;
	private static final long waitTime = 120000;
	

	
	public GameCreator(){
		games = new ArrayList<Game>();
		playersInGames = new HashMap<String, Game>();
		observersInGames = new HashMap<Observer, Game>();
		pendingGame = new Game();
		
		timer = new Timer();
		timerTask = new GameCreatorTimerTask();

	}
	
	public void addPlayer(String ID, String bonusTile, Observer o) throws Exception{
		if(playerExists(ID)) throw new Exception("Player already exists");
		
		if(pendingGame.hasStarted()){
			games.add(pendingGame);
			pendingGame = new Game();
			timerTask.cancel();
			timer.purge();
		}
		
		pendingGame.addPlayer(ID, bonusTile, o);
		playersInGames.put(ID, pendingGame);
		observersInGames.put(o, pendingGame);
		
		if(pendingGame.getNumberOfPlayers() >= 2 && !pendingGame.hasStarted()){
			timerTask.cancel();
			timer.purge();
			timerTask = new GameCreatorTimerTask();
			timer.schedule(timerTask, waitTime);
		}
		
		

	}
	
	public void reconnect(String ID, Observer o) throws Exception{
		if(!playerExists(ID)) throw new Exception("Player with that name does not exist");
		getGameFromPlayerID(ID).reconnect(ID, o);
		observersInGames.put(o, pendingGame);
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
	
	private class GameCreatorTimerTask extends TimerTask{


		@Override
		public void run() {
			try {
				pendingGame.start();
				games.add(pendingGame);
				pendingGame = new Game();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

	}
}
