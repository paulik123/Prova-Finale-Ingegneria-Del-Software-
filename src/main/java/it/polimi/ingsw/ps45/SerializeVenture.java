package it.polimi.ingsw.ps45;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.polimi.ingsw.ps45.model.actions.state.AddServantsToHarvestState;
import it.polimi.ingsw.ps45.model.cards.Era;
import it.polimi.ingsw.ps45.model.cards.Venture;
import it.polimi.ingsw.ps45.model.effects.CollectEffect;
import it.polimi.ingsw.ps45.model.effects.CouncilPrivilegeOneEffect;
import it.polimi.ingsw.ps45.model.effects.CouncilPrivilegeTwoEffect;
import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.effects.HarvestWithValueEffect;
import it.polimi.ingsw.ps45.model.effects.ProductionWithValueEffect;
import it.polimi.ingsw.ps45.model.effects.TakeAnyCardEffect;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;

public class SerializeVenture {
	
	public static void main(String[] args) throws IOException{
		ConsumableSet costOne = new ConsumableSet();
		costOne.setCoins(2);
		costOne.setWood(2);
		costOne.setStone(2);
		
		
		ConsumableSet costTwo = new ConsumableSet();
		costTwo.setCoins(2);
		costTwo.setWood(2);
		costTwo.setStone(2);
	
		
		ConsumableSet reqOne = new ConsumableSet();
		reqOne.setCoins(2);
		reqOne.setWood(2);
		reqOne.setStone(2);
		
		ConsumableSet reqTwo = new ConsumableSet();
		reqTwo.setCoins(2);
		reqTwo.setWood(2);
		reqTwo.setStone(2);
		
		
		ConsumableSet immCons = new ConsumableSet();
		immCons.setFaithPoints(2);

		
		ConsumableSet endCons = new ConsumableSet();
		endCons.setVictoryPoints(6);
		
		
		CollectEffect immediateEff = new CollectEffect(immCons);
		CollectEffect endGameEff = new CollectEffect(endCons);
		CouncilPrivilegeOneEffect cpeffect = new CouncilPrivilegeOneEffect();
		//CouncilPrivilegeTwoEffect cpeffect = new CouncilPrivilegeTwoEffect();
		
		//TakeAnyCardEffect takeEffect = new TakeAnyCardEffect(7, new ConsumableSet());
		
	    Venture venture = new Venture(Era.II, "Riparare l'Abbazia", costOne, costTwo, reqOne, reqTwo);
	    venture.addEffect(immediateEff);
	    //venture.addEffect(cpeffect);
	    venture.addEndGameEffect(endGameEff);
	    
        Writer writer = new FileWriter("Riparare l'Abbazia.json");

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Effect.class,
                        new PropertyBasedInterfaceMarshal()).create();
        gson.toJson(venture, writer);

        writer.close();
        
        Venture vent = gson.fromJson(new FileReader("Riparare l'Abbazia.json"), Venture.class);
        System.out.println(vent.getName());
        System.out.println(vent.getEra());
        System.out.println(vent.costI().getCoins());
	    
	    
	}

	

}

