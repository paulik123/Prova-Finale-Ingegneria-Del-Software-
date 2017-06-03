package it.polimi.ingsw.ps45.model.game;

import java.util.ArrayList;

public class GameUpdateNotifier extends Thread implements Notifier{
	private ArrayList<Observer> observers;
	private String resp;

	
	public GameUpdateNotifier(ArrayList<Observer> observers, String resp){
		this.observers = observers;
		this.resp = resp + "\n";
	}
	
	public GameUpdateNotifier(Observer observer, String resp){
		observers = new ArrayList<Observer>();
		observers.add(observer);
		this.resp = resp + "\n";
	}
	


	@Override
	public void run() {
		ServerResponseWrapper response = new ServerResponseWrapper(new GameUpdateResponse(resp));
		for(Observer o: observers){
			o.notify(response);
		}
	}
}
