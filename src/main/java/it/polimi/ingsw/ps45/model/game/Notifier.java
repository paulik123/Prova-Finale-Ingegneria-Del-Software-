package it.polimi.ingsw.ps45.model.game;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.controller.Observer;

public class Notifier extends Thread{
	ArrayList<Observer> observers;
	String json;
	
	public Notifier(ArrayList<Observer> observers, String json){
		this.observers = observers;
		this.json = json + "\n";
	}
	
	public Notifier(Observer observer, String json){
		observers = new ArrayList<Observer>();
		observers.add(observer);
		this.json = json + "\n";
	}

	@Override
	public void run() {
		for(Observer o: observers){
			o.notify(json);
		}
	}
	
	
}
