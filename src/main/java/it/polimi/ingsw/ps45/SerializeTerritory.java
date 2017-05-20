package it.polimi.ingsw.ps45;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import it.polimi.ingsw.ps45.model.cards.Era;
import it.polimi.ingsw.ps45.model.cards.Territory;
import it.polimi.ingsw.ps45.model.effects.CollectEffect;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;

/**
 * Hello world!
 *
 */
public class SerializeTerritory 
{
    public static void main( String[] args )
    {
    	ConsumableSet immediate = new ConsumableSet();
    	immediate.setCoins(1);
    	
    	ConsumableSet harvest = new ConsumableSet();
    	harvest.setCoins(2);

        CollectEffect immEff = new CollectEffect(immediate);
        CollectEffect harvestEff = new CollectEffect(harvest);
        
        Territory t = new Territory(Era.I, "Miniera d'Oro", immEff, harvestEff, 1);
        try {
	         FileOutputStream fileOut = new FileOutputStream("C://outout//Miniera.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(t);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in /Miniera d'Oro.ser\n\n");
	      }catch(IOException i) {
	         i.printStackTrace();
	      }
	      
        Territory x = null;
        try {
	         FileInputStream fileIn = new FileInputStream("C://outout//Miniera.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         x = (Territory) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i) {
	         i.printStackTrace();
	         return;
	      }catch(Exception ex) {
	         System.out.println("Employee class not found");
	         ex.printStackTrace();
	         return;
	      }
        System.out.println(t.getEra());
        System.out.println(t.getEra().equals(Era.I));
    }
}
