package it.polimi.ingsw.ps45;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import it.polimi.ingsw.ps45.model.cards.Era;
import it.polimi.ingsw.ps45.model.cards.Territory;
import it.polimi.ingsw.ps45.model.effects.CollectEffect;
import it.polimi.ingsw.ps45.model.effects.CouncilPrivilegeOneEffect;
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
    	immediate.setCoins(3);

    	
    	ConsumableSet harvest = new ConsumableSet();

        CollectEffect immEff = new CollectEffect(immediate);
        CollectEffect harvestEff = new CollectEffect(harvest);
        
        CouncilPrivilegeOneEffect cpeffect = new CouncilPrivilegeOneEffect();
        
        
        Territory t = new Territory(Era.I, "Citta", 6);
        t.addEffect(immEff);
        t.addHarvestEffect(cpeffect);
        try {
	         FileOutputStream fileOut = new FileOutputStream("Citta.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(t);
	         out.close();
	         fileOut.close();
        }
        catch(IOException i) {
	         i.printStackTrace();
	      }
        Territory x = null;
        try {
	         FileInputStream fileIn = new FileInputStream("Citta.ser");
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
        System.out.println(x.getName());
    }
}
