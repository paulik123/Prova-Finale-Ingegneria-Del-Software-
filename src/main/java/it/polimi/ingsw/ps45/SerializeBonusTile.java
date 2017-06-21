package it.polimi.ingsw.ps45;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.polimi.ingsw.ps45.gson.GsonWithInterface;
import it.polimi.ingsw.ps45.gson.PropertyBasedInterfaceMarshal;
import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.player.PersonalBonusTile;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;

public class SerializeBonusTile {

	public static void main(String[] args) {
		ConsumableSet production = new ConsumableSet();
		production.setWood(0);
		production.setStone(0);
		production.setCoins(2);
		production.setMilitaryPoins(0);
		production.setServants(1);
		production.setFaithPoints(0);
    	
    	ConsumableSet harvest = new ConsumableSet();
    	harvest.setWood(1);
    	harvest.setStone(1);
    	harvest.setCoins(0);
    	harvest.setMilitaryPoins(1);
    	harvest.setServants(0);
    	harvest.setFaithPoints(0);
    	
    	PersonalBonusTile bonusTile = new PersonalBonusTile(production, harvest, "5", 1, 1);
    	
    	String path = "serialized\\bonustiles\\5.json";	
        Writer writer;
		try {
			writer = new FileWriter(path);
	        Gson gson = GsonWithInterface.getGson();
	        gson.toJson(bonusTile, writer);

	        writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
