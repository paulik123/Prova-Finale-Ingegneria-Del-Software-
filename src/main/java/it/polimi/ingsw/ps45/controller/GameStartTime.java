package it.polimi.ingsw.ps45.controller;



import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.polimi.ingsw.ps45.gson.GsonWithInterface;


/**
 * Object that reads the game start delay from file.
 */
public class GameStartTime {
	
	private long time;
	private int seconds;
	
	public GameStartTime(){
		
		Gson gson = GsonWithInterface.getGson();
		try {
			seconds = gson.fromJson(new FileReader("serialized\\time\\gameStartDelayTime.json"), Integer.class);
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			seconds = 120;
		}
		time = 1000 * (long)seconds;
	}
	
	public long getTime(){
		return time;
	}

}