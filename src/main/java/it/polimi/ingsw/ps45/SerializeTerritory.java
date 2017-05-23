package it.polimi.ingsw.ps45;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.polimi.ingsw.ps45.gson.PropertyBasedInterfaceMarshal;
import it.polimi.ingsw.ps45.model.area.NoCardArea;
import it.polimi.ingsw.ps45.model.cards.Era;
import it.polimi.ingsw.ps45.model.cards.Territory;
import it.polimi.ingsw.ps45.model.effects.CollectEffect;
import it.polimi.ingsw.ps45.model.effects.CouncilPrivilegeOneEffect;
import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;

/**
 * Hello world!
 *
 */
public class SerializeTerritory 
{
    public static void main( String[] args ) throws IOException
    {
    	
    	ConsumableSet immediate = new ConsumableSet();
    	immediate.setWood(1);
    	immediate.setStone(0);
    	immediate.setCoins(0);
    	immediate.setMilitaryPoins(0);
    	immediate.setServants(0);
    	immediate.setFaithPoints(0);
    	
    	ConsumableSet harvest = new ConsumableSet();
    	harvest.setWood(0);
    	harvest.setStone(3);
    	harvest.setCoins(0);
    	harvest.setMilitaryPoins(0);
    	harvest.setServants(0);
    	harvest.setFaithPoints(0);
    	
        CollectEffect immEff = new CollectEffect(immediate);
        CollectEffect harvestEff = new CollectEffect(harvest);
        
        CouncilPrivilegeOneEffect cpeffect = new CouncilPrivilegeOneEffect();
        
        
        Territory t = new Territory(Era.II, "Citta", 6);
        t.addEffect(immEff);
        //t.addEffect(cpeffect);
        t.addHarvestEffect(harvestEff);

        Writer writer = new FileWriter("C://outout//json//Citta.json");

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Effect.class,
                        new PropertyBasedInterfaceMarshal()).create();
        gson.toJson(t, writer);

        writer.close();
        
        Territory aaa = gson.fromJson(new FileReader("C://outout//json//Citta.json"), Territory.class);
        System.out.println(aaa.getName());
    }
}