package it.polimi.ingsw.ps45.model.vatican;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Random;

import it.polimi.ingsw.ps45.model.cards.Era;

public class Vatican {
	private HashMap<Era, ExcommunicationCard> map;
	
	public Vatican(){
		File dirI = new File("serialized//excom//I");
		File[] filesI = dirI.listFiles();
		
		File dirII = new File("serialized//excom//II");
		File[] filesII = dirII.listFiles();
		
		File dirIII = new File("serialized//excom//III");
		File[] filesIII = dirIII.listFiles();
		
		map = new HashMap<Era, ExcommunicationCard>();
		
		Random r = new Random();
		map.put(Era.I, fromFile(filesI[r.nextInt(7)].getPath()));
		map.put(Era.II, fromFile(filesII[r.nextInt(7)].getPath()));
		map.put(Era.III, fromFile(filesIII[r.nextInt(7)].getPath()));
	}
	public ExcommunicationCard fromFile(String path){
		ExcommunicationCard x = null;
	    try {
	         FileInputStream fileIn = new FileInputStream(path);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         x = (ExcommunicationCard) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i) {
	         i.printStackTrace();
	      }catch(Exception ex) {
	         ex.printStackTrace();
	      }
	    return x;
	    
	}


}
