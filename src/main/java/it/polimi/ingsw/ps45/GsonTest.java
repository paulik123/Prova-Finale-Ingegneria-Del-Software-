package it.polimi.ingsw.ps45;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import it.polimi.ingsw.ps45.model.cards.Era;
import it.polimi.ingsw.ps45.model.cards.Territory;
import it.polimi.ingsw.ps45.model.effects.CollectEffect;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;

public class GsonTest {
	public static void main(String[] args) throws JsonIOException, IOException{

		
		
		ConsumableSet cs = new ConsumableSet();
		cs.setWood(3);
		cs.setStone(5);
		CollectEffect c = new CollectEffect(cs);
		
		Territory ter = new Territory(Era.I, "Doinitza", c, c, 1);
		Gson gson = new GsonBuilder().create();
		Writer writer = new FileWriter("territory.json");
		gson.toJson(ter, Territory.class, writer);
		System.out.println(ter.getName());
	}

}
