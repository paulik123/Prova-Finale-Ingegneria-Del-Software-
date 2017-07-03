package it.polimi.ingsw.ps45.model.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;

import it.polimi.ingsw.ps45.gson.GsonWithInterface;

/**
 * Class that notifies a list of observers when the game state has been changed. It notifies the observers with a new serilized version of the game.
 */
public class GameUpdateNotifier extends Thread implements Notifier{
	private ArrayList<Observer> observers;
	private String resp;
	private Game g;

	
	/**
 	 * Constructor
 	 * @param observers a list with the observers that will be notified.
 	 * @param resp the message explaining the error.
 	 * @param game the game that created the observer. It is used in order to delete the observer in the game's observer list if the observer disconnected.
	 */
	public GameUpdateNotifier(ArrayList<Observer> observers, String resp, Game game){
		this.observers = observers;
		this.resp = resp + "\n";
		this.g = game;
	}

	

	/**
 	 * The notifier runs in a new thread so it doesn't block the game.
 	 * The response is put in a wrapper class then serialized as a json string.
 	 * Removes the observer if the it gets an exception / socket is closed.
	 */
	@Override
	public void run() {
		ServerResponseWrapper response = new ServerResponseWrapper(new GameUpdateResponse(resp));
		
    	Gson gson = GsonWithInterface.getGson();
    	String json = gson.toJson(response);
    	for(Observer o:observers){
    		try {
    			o.notify(json);
    		} catch (IOException e) {
    			g.removeObserver(o);
    		}
    	}
	}
}
