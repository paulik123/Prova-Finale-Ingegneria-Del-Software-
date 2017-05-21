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
    	immediate.setWood(1);

    	
    	ConsumableSet harvest = new ConsumableSet();
    	harvest.setStone(3);

        CollectEffect immEff = new CollectEffect(immediate);
        CollectEffect harvestEff = new CollectEffect(harvest);
        
       // CouncilPrivilegeOneEffect cpeffect = new CouncilPrivilegeOneEffect();
        
        
        Territory t = new Territory(Era.II, "Cava di Pietra", 3);
        t.addEffect(immEff);
        t.addHarvestEffect(harvestEff);
        try {
	         FileOutputStream fileOut = new FileOutputStream("Cava di Pietra.ser");
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
	         FileInputStream fileIn = new FileInputStream("Cava di Pietra.ser");
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
