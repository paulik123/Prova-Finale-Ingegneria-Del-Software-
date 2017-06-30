package it.polimi.ingsw.ps45.model.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;

import it.polimi.ingsw.ps45.gson.GsonWithInterface;

public class GameUpdateNotifier extends Thread implements Notifier{
	private ArrayList<Observer> observers;
	private String resp;
	private Game g;

	
	public GameUpdateNotifier(ArrayList<Observer> observers, String resp, Game game){
		this.observers = observers;
		this.resp = resp + "\n";
		this.g = game;
	}

	

	// Removes the observer if the it gets an exception / socket is closed.
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
