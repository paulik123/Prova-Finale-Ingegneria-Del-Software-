package it.polimi.ingsw.ps45.model.game;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;

import it.polimi.ingsw.ps45.gson.GsonWithInterface;

/**
 * Class that notifies a list of observers when an error occurred or an actions is illegal.
 */
public class ErrorNotifier extends Thread implements Notifier{
	private ArrayList<Observer> observers;
	private String resp;

	
	/**
 	 * Constructor
 	 * @param observers a list with the observers that will be notified.
 	 * @param resp the message explaining the error.
	 */
	public ErrorNotifier(ArrayList<Observer> observers, String resp){
		this.observers = observers;
		this.resp = resp + "\n";
	}
	
	/**
 	 * Constructor
 	 * @param observer a single observer that will be notified.
 	 * @param resp the message explaining the error.
	 */
	public ErrorNotifier(Observer observer, String resp){
		observers = new ArrayList<Observer>();
		observers.add(observer);
		this.resp = resp + "\n";
	}
	


	/**
 	 * The notifier runs in a new thread so it doesn't block the game.
 	 * The response is put in a wrapper class then serialized as a json string.
	 */
	@Override
	public void run() {
		ServerResponseWrapper response = new ServerResponseWrapper(new ErrorResponse(resp));
		
    	Gson gson = GsonWithInterface.getGson();
    	
    	String json = gson.toJson(response);
		for(Observer o: observers){
			try {
				o.notify(json);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
