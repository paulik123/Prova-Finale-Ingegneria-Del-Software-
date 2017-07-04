package it.polimi.ingsw.ps45.model.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.ps45.model.actions.state.NoActionState;
import it.polimi.ingsw.ps45.model.actions.state.PawnActionState;
import it.polimi.ingsw.ps45.model.player.Player;


/**
 * Class that contains all the logic regarding rounds/turns.
 */
public class Round{
	
	private static final Logger LOGGER = Logger.getLogger( Round.class.getName());
	private Player[] turnOrder;
	int index;
	private boolean hasEnded;
	private transient Timer timer;
	private transient TurnTimerTask timerTask;
	private  long turnTime;
	private transient ArrayList<Observer> observers;
	
	
	/**
 	 * Constructor
 	 * @param turnOrder an array with players in order of their turns.
 	 * @param o an observer that gets notified when the player turn timer expires and nextTurn() is called.
	 */
	public Round(Player[] turnOrder, Observer o){
		timer = new Timer();
		
		hasEnded = false;
		this.turnOrder = turnOrder;
		index = 0;
		
		observers = new ArrayList<Observer>();
		observers.add(o);
		
		turnTime = new TurnTime().getTime();
		
		// Setting up timer
		timerTask = new TurnTimerTask();
		timer.schedule(timerTask, turnTime);
	}
	
	/**
 	 * Resets the timer every time it is called.
 	 * If there are no more turns in the round the round is marked as ended.
 	 * Skips the turn if the player is disconnected
	 */
	public synchronized void nextTurn(){
		// Canceling the last timerTask because player has responded before the timer ran out.
		timerTask.cancel();
		timer.purge();
		timerTask = new TurnTimerTask();
		timer.schedule(timerTask, turnTime);
		
		turnOrder[index].getActionBuilder().setState(new NoActionState());
		
		if(index < turnOrder.length-1){
			index++;
			turnOrder[index].getActionBuilder().setState(new PawnActionState());
			//Skip the turn if the player is diconnected
			if(turnOrder[index].isDisconnected()) nextTurn();
		}
		else{
			hasEnded = true;
			timerTask.cancel();
			timer.cancel();
		}
	}

	/**
 	 * @return the player who can do actions in the current turn.
	 */
	public Player getCurrentPlayer() {
		return turnOrder[index];
	}
	
	/**
 	 * @return true if the round has ended.
	 */
	public boolean roundEnded(){
		return hasEnded;
	}
	
	/**
 	 * Notifies the observer when the timer has expired and the round entered a new turn.
	 */
	public synchronized void notifyObservers(){
		for(Observer o: observers){
				try {
					o.notify("");
				} catch (IOException e) {
					LOGGER.log(Level.SEVERE, "context", e);
				}
		}
	}
	
	/**
 	 * Class that defines what should happen when the timer expires: nextTurn() is called and the observers are notified.
	 */
	private class TurnTimerTask extends TimerTask{
		
		@Override
		public void run() {
			System.out.println(getCurrentPlayer().getPlayerID()+"'s time expired. Proceeding to next turn.");
			nextTurn();
			notifyObservers();
		}

	}
	

	
	
	
}
