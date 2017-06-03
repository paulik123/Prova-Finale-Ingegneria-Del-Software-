package it.polimi.ingsw.ps45.model.game;

import java.util.ArrayList;

public class ErrorNotifier extends Thread implements Notifier{
	private ArrayList<Observer> observers;
	private String resp;

	
	public ErrorNotifier(ArrayList<Observer> observers, String resp){
		this.observers = observers;
		this.resp = resp + "\n";
	}
	
	public ErrorNotifier(Observer observer, String resp){
		observers = new ArrayList<Observer>();
		observers.add(observer);
		this.resp = resp + "\n";
	}
	


	@Override
	public void run() {
		ServerResponseWrapper response = new ServerResponseWrapper(new ErrorResponse(resp));
		for(Observer o: observers){
			o.notify(response);
		}
	}
}
