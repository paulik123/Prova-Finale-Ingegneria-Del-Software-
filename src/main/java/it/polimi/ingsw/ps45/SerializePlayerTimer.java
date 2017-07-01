package it.polimi.ingsw.ps45;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.google.gson.Gson;

import it.polimi.ingsw.ps45.gson.GsonWithInterface;

public class SerializePlayerTimer {
	
	public static void main (String[] args){
		String path = "serialized\\time\\gameStartDelayTime.json";	
        Writer writer;
        
        Integer seconds = 120;
		try {
			writer = new FileWriter(path);
	        Gson gson = GsonWithInterface.getGson();
	        gson.toJson(seconds, writer);

	        writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
