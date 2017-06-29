package it.polimi.ingsw.ps45.model.game;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import it.polimi.ingsw.ps45.model.actions.state.NoActionState;
import it.polimi.ingsw.ps45.model.actions.state.PawnActionState;
import it.polimi.ingsw.ps45.model.player.Player;

public class Round{
	private Player[] turnOrder;
	int index;
	private boolean hasEnded;
	private transient Timer timer;
	private transient TurnTimerTask timerTask;
	private static final long turnTime = 60000;
	private transient ArrayList<Observer> observers;
	
	public Round(Player[] turnOrder, Observer o){
		timer = new Timer();
		
		hasEnded = false;
		this.turnOrder = turnOrder;
		index = 0;
		
		observers = new ArrayList<Observer>();
		observers.add(o);
		
		// Setting up timer
		timerTask = new TurnTimerTask();
		timer.schedule(timerTask, turnTime);
	}
	
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
		}
		else{
			hasEnded = true;
			timerTask.cancel();
			timer.cancel();
		}
	}

	public Player getCurrentPlayer() {
		return turnOrder[index];
	}
	
	public boolean roundEnded(){
		return hasEnded;
	}
	
	public synchronized void notifyObservers(){
		for(Observer o: observers){
				o.notify("");
		}
	}
	
	private class TurnTimerTask extends TimerTask{
		

		@Override
		public void run() {
			System.out.println(getCurrentPlayer().getPlayerID()+"'s time expired. Proceeding to next turn.");
			nextTurn();
			notifyObservers();
		}

	}
	

	
	
	
}
