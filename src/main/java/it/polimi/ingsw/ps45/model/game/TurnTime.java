package it.polimi.ingsw.ps45.model.game;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.polimi.ingsw.ps45.gson.GsonWithInterface;

public class TurnTime {
	
	private long time;
	private int seconds;
	
	public TurnTime(){
		
		Gson gson = GsonWithInterface.getGson();
		try {
			seconds = gson.fromJson(new FileReader("serialized\\time\\playerTurnTime.json"), Integer.class);
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
