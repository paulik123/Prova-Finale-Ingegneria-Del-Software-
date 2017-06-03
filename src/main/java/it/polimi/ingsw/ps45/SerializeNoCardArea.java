package it.polimi.ingsw.ps45;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.polimi.ingsw.ps45.gson.PropertyBasedInterfaceMarshal;
import it.polimi.ingsw.ps45.model.area.NoCardArea;
import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.effects.HarvestEffect;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;

public class SerializeNoCardArea {
	public static void main(String[] args) throws IOException{
		
		ConsumableSet effectConsumableSet = new ConsumableSet();
		effectConsumableSet.setWood(0);
		effectConsumableSet.setStone(0);
		effectConsumableSet.setCoins(1);
		effectConsumableSet.setMilitaryPoins(0);
		effectConsumableSet.setServants(0);
		effectConsumableSet.setFaithPoints(0);
		effectConsumableSet.setVictoryPoints(0);
		
		String path = "C://outout//json//HarvestBigArea.json";
		
		//CollectEffect collectEffect = new CollectEffect(effectConsumableSet);
		//CouncilPrivilegeOneEffect e = new CouncilPrivilegeOneEffect();
		HarvestEffect harvestEffect = new HarvestEffect(3);
		
		//CouncilPrivilegeOneAndCollectEffect e = new CouncilPrivilegeOneAndCollectEffect(effectConsumableSet);
		
    	NoCardArea area = new NoCardArea(1,999, harvestEffect, "name");

	      
        Writer writer = new FileWriter(path);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Effect.class,
                        new PropertyBasedInterfaceMarshal()).create();
        gson.toJson(area, writer);

        writer.close();
        
        
        
        
        NoCardArea aaa = gson.fromJson(new FileReader(path), NoCardArea.class);
        System.out.println(aaa.getCost());


	}
}