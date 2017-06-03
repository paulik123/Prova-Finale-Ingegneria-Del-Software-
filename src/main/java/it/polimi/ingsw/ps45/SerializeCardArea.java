package it.polimi.ingsw.ps45;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.polimi.ingsw.ps45.gson.PropertyBasedInterfaceMarshal;
import it.polimi.ingsw.ps45.model.area.cardarea.VentureCardArea;
import it.polimi.ingsw.ps45.model.effects.CollectEffect;
import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.effects.NoEffect;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;

public class SerializeCardArea {
	public static void main(String[] args) throws IOException{
		
		ConsumableSet effectConsumableSet = new ConsumableSet();
		effectConsumableSet.setWood(0);
		effectConsumableSet.setStone(0);
		effectConsumableSet.setCoins(2);
		effectConsumableSet.setMilitaryPoins(0);
		effectConsumableSet.setServants(0);
		effectConsumableSet.setFaithPoints(0);
		effectConsumableSet.setVictoryPoints(0);
		
		String path = "C://outout//json//VentureCardAreaThirdFloor.json";
		
		CollectEffect collectEffect = new CollectEffect(effectConsumableSet);
		//CouncilPrivilegeOneEffect e = new CouncilPrivilegeOneEffect();
		//HarvestEffect harvestEffect = new HarvestEffect(3);
		
		//CouncilPrivilegeOneAndCollectEffect e = new CouncilPrivilegeOneAndCollectEffect(effectConsumableSet);
		NoEffect noEffect = new NoEffect();
		
    	VentureCardArea area = new VentureCardArea(7, collectEffect, "name");

	      
        Writer writer = new FileWriter(path);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Effect.class,
                        new PropertyBasedInterfaceMarshal()).create();
        gson.toJson(area, writer);

        writer.close();
        
        
        
        
        VentureCardArea aaa = gson.fromJson(new FileReader(path), VentureCardArea.class);
        System.out.println(aaa.getCost());


	}
}