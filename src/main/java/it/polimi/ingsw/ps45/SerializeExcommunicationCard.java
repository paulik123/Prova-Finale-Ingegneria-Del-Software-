package it.polimi.ingsw.ps45;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.polimi.ingsw.ps45.gson.PropertyBasedInterfaceMarshal;
import it.polimi.ingsw.ps45.model.cards.Era;
import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.effects.ResourceVictoryPointsPenaltyEffect;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.vatican.ExcommunicationCard;

public class SerializeExcommunicationCard {
	public static void main( String[] args ) throws IOException
    {
    	

    	ConsumableSet cs = new ConsumableSet();
    	cs.setWood(1);
    	cs.setStone(1);
    	cs.setCoins(0);
    	cs.setMilitaryPoins(0);
    	cs.setServants(0);
    	cs.setFaithPoints(0);
    	
    	ResourceVictoryPointsPenaltyEffect effect = new ResourceVictoryPointsPenaltyEffect();
    	
        
        
        ExcommunicationCard c = new ExcommunicationCard(Era.III, effect);

        Writer writer = new FileWriter("C://outout//excomjson//ResourceVictoryPointsPenalty.json");

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Effect.class,
                        new PropertyBasedInterfaceMarshal()).create();
        gson.toJson(c, writer);

        writer.close();
    }
}
