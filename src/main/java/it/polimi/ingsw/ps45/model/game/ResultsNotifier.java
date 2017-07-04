package it.polimi.ingsw.ps45.model.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;

import it.polimi.ingsw.ps45.gson.GsonWithInterface;

/**
 * Class that notifies a list of observers with the results when the game has ended.
 */
public class ResultsNotifier extends Thread implements Notifier{
	
	private static final Logger LOGGER = Logger.getLogger( ResultsNotifier.class.getName());
	private ArrayList<Observer> observers;
	private String resp;

	
	/**
 	 * Constructor
 	 * @param observers a list with the observers that will be notified.
 	 * @param resp the message explaining the error.
	 */
	public ResultsNotifier(ArrayList<Observer> observers, String resp){
		this.observers = observers;
		this.resp = resp + "\n";
	}
	
	/**
 	 * Constructor
 	 * @param observer a single observer that will be notified.
 	 * @param resp the message explaining the error.
	 */
	public ResultsNotifier(Observer observer, String resp){
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
		ServerResponseWrapper response = new ServerResponseWrapper(new ResultsResponse(resp));
		
    	Gson gson = GsonWithInterface.getGson();
    	
    	String json = gson.toJson(response);
		for(Observer o: observers){
			try {
				o.notify(json);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				LOGGER.log(Level.SEVERE, "context", e);
			}
		}
	}
}
