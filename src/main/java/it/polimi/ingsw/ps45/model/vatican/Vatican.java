package it.polimi.ingsw.ps45.model.vatican;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.polimi.ingsw.ps45.gson.PropertyBasedInterfaceMarshal;
import it.polimi.ingsw.ps45.model.cards.Era;
import it.polimi.ingsw.ps45.model.cards.Territory;
import it.polimi.ingsw.ps45.model.effects.Effect;

public class Vatican {
	private HashMap<Era, ExcommunicationCard> map;
	
	public Vatican(){
		File dirI = new File("serialized//cards//excom//I");
		File[] filesI = dirI.listFiles();
		
		File dirII = new File("serialized//cards//excom//II");
		File[] filesII = dirII.listFiles();
		
		File dirIII = new File("serialized//cards//excom//III");
		File[] filesIII = dirIII.listFiles();
		
		map = new HashMap<Era, ExcommunicationCard>();
		
		Random r = new Random();
		map.put(Era.I, fromFile(filesI[r.nextInt(filesI.length)].getPath()));
		map.put(Era.II, fromFile(filesII[r.nextInt(filesII.length)].getPath()));
		map.put(Era.III, fromFile(filesIII[r.nextInt(filesIII.length)].getPath()));
	}
	
	
	public ExcommunicationCard fromFile(String path){
		 Gson gson = new GsonBuilder()
	                .registerTypeAdapter(Effect.class,
	                        new PropertyBasedInterfaceMarshal()).create();
		 
		 ExcommunicationCard c = null;
		try {
			c = gson.fromJson(new FileReader(path), ExcommunicationCard.class);
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return c;
	    
	}
	
	public ExcommunicationCard getCard(Era e){
		return map.get(e);
	}


}
