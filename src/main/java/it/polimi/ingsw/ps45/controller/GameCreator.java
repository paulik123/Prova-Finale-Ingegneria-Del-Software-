package it.polimi.ingsw.ps45.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.ps45.exceptions.PlayerExistanceException;
import it.polimi.ingsw.ps45.model.game.Game;
import it.polimi.ingsw.ps45.model.game.Observer;


/**
 * The object that manages existing games and creates new ones when it's needed.
 * Has a timer that starts the pending games if two players already joined it but no other new player joined in a predefined time.
 */
public class GameCreator {
	
	private static final Logger LOGGER = Logger.getLogger( GameCreator.class.getName() );
	private ArrayList<Game> games;
	private Game pendingGame;
	private HashMap<String, Game> playersInGames;
	private Timer timer;
	private GameCreatorTimerTask timerTask;
	private long waitTime;
	

	/**
 	 * Constructor
 	 * Reads the game start time delay from file.
 	 * Initializes the list of games and a pendingGame that is waiting to start.
	 */
	public GameCreator(){
		games = new ArrayList<Game>();
		playersInGames = new HashMap<String, Game>();
		pendingGame = new Game();
		
		waitTime = new GameStartTime().getTime();
		
		timer = new Timer();
		timerTask = new GameCreatorTimerTask();

	}
	
	/**
	 * 
	 * @param  ID  a identification string. must be unique on the server.
	 * @param  bonusTile the index the bonusTile serialized file.
	 * @param  o An observer so that the game knows where to send gameUpdates and errors to.
	 * @throws PlayerExistanceException If a player with that ID doesn't exists on the server.
	 * @throws Exception
	 */
	public void addPlayer(String ID, String bonusTile, Observer o) throws PlayerExistanceException, Exception{
		if(playerExists(ID)) throw new PlayerExistanceException();
		
		if(pendingGame.hasStarted()){
			games.add(pendingGame);
			pendingGame = new Game();
			timerTask.cancel();
			timer.purge();
		}
		
		pendingGame.addPlayer(ID, bonusTile, o);
		playersInGames.put(ID, pendingGame);
		
		if(pendingGame.getNumberOfPlayers() >= 2 && !pendingGame.hasStarted()){
			timerTask.cancel();
			timer.purge();
			timerTask = new GameCreatorTimerTask();
			timer.schedule(timerTask, waitTime);
		}
	}
	
	/**
	 * 
	 * @throws PlayerExistanceException  If a player with that ID doesn't exists on the server.
	 * @param  ID  a identification string. The ID must already exist on the server so gameCreator knows to which the player should be reconnected to.
	 * @param  o An observer so that the game knows where to send gameUpdates and errors to.
	 */
	public void reconnect(String ID, Observer o) throws PlayerExistanceException{
			getGameFromPlayerID(ID).reconnect(ID, o);
	}
	
	/**
	 * 
	 * @param  ID  a identification string of the player.
	 * @return true if a player with the ID in params exists on the server.
	 */
	private boolean playerExists(String ID){
		for(String s:playersInGames.keySet()){
			if(s.equals(ID)) return true;
		}
		return false;
	}
	
	/**
	 * @throws PlayerExistanceException  If a player with that ID doesn't exists on the server.
	 * @param  ID  a identification string of the player.
	 * @return the game in which a player with that ID exists.
	 */
	public Game getGameFromPlayerID(String ID) throws PlayerExistanceException{
		if(!playerExists(ID)) throw new PlayerExistanceException();
		return playersInGames.get(ID);
	}
	
	/**
	 * A class which defines a task runs when the timer expires.
	 * The current pending game is started and a new pending games is created.
	 */
	private class GameCreatorTimerTask extends TimerTask{
		@Override
		public void run() {
			try {
				pendingGame.start();
				games.add(pendingGame);
				pendingGame = new Game();
			} catch (Exception e) {
				LOGGER.log(Level.SEVERE, "context", e);
			}
			
		}

	}
}
