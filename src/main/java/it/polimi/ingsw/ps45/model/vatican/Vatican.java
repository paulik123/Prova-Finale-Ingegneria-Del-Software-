package it.polimi.ingsw.ps45.model.vatican;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.polimi.ingsw.ps45.gson.PropertyBasedInterfaceMarshal;
import it.polimi.ingsw.ps45.model.cards.Era;
import it.polimi.ingsw.ps45.model.effects.Effect;

/**
 * Vatican reads from file and manages all excommunication cards.
 */

public class Vatican {
	private static final Logger LOGGER = Logger.getLogger( Vatican.class.getName());
	public static VaticanVictoryPointsConverter converter = new VaticanVictoryPointsConverter();
	private HashMap<Era, ExcommunicationCard> map;
	
	/**
 	 * Constructor
	 * Reads a random Excommunication card for each era.
	 */
	public Vatican(){
		File dirI = new File("serialized/cards/excom/I");
		File[] filesI = dirI.listFiles();
		
		File dirII = new File("serialized/cards/excom/II");
		File[] filesII = dirII.listFiles();
		
		File dirIII = new File("serialized/cards/excom/III");
		File[] filesIII = dirIII.listFiles();
		
		map = new HashMap<Era, ExcommunicationCard>();
		
		Random r = new Random();
		map.put(Era.I, fromFile(filesI[r.nextInt(filesI.length)].getPath()));
		map.put(Era.II, fromFile(filesII[r.nextInt(filesII.length)].getPath()));
		map.put(Era.III, fromFile(filesIII[r.nextInt(filesIII.length)].getPath()));
	}
	
	/**
	 * @param path the path of the file in which the excom card is serialized.
	 * @return a deserialized excom card.
	 */
	public ExcommunicationCard fromFile(String path){
		 Gson gson = new GsonBuilder()
	                .registerTypeAdapter(Effect.class,
	                        new PropertyBasedInterfaceMarshal()).create();
		 
		 ExcommunicationCard c = null;
		try {
			c = gson.fromJson(new FileReader(path), ExcommunicationCard.class);
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, "context", e);
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, "context", e);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, "context", e);
		}
		 return c;
	    
	}
	
	/**
	 * @param e the era which the excommunication card should belong to.
	 * @return an excommunication card that belong to the given era.
	 */
	public ExcommunicationCard getCard(Era e){
		return map.get(e);
	}


}
