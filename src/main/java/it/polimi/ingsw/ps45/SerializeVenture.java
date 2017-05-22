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
		costOne.setCoins(8);
		
		
		ConsumableSet costTwo = new ConsumableSet();
		costTwo.setCoins(8);
	
		
		ConsumableSet reqOne = new ConsumableSet();
		reqOne.setCoins(8);
		
		ConsumableSet reqTwo = new ConsumableSet();
		reqTwo.setCoins(8);
		
		
		ConsumableSet immCons = new ConsumableSet();
		immCons.setMilitaryPoins(7);

		
		ConsumableSet endCons = new ConsumableSet();
		endCons.setVictoryPoints(6);
		
		
		CollectEffect immediateEff = new CollectEffect(immCons);
		CollectEffect endGameEff = new CollectEffect(endCons);
		CouncilPrivilegeOneEffect cpeffect = new CouncilPrivilegeOneEffect();
		//CouncilPrivilegeTwoEffect cpeffect = new CouncilPrivilegeTwoEffect();
		
		//TakeAnyCardEffect takeEffect = new TakeAnyCardEffect(7, new ConsumableSet());
		
	    Venture venture = new Venture(Era.III, "Ingaggiare Mercenari", costOne, costTwo, reqOne, reqTwo);
	    venture.addEffect(immediateEff);
	    //venture.addEffect(cpeffect);
	    venture.addEndGameEffect(endGameEff);
	    
        Writer writer = new FileWriter("Ingaggiare Mercenari.json");

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Effect.class,
                        new PropertyBasedInterfaceMarshal()).create();
        gson.toJson(venture, writer);

        writer.close();
        
        Venture vent = gson.fromJson(new FileReader("Ingaggiare Mercenari.json"), Venture.class);
        System.out.println(vent.getName());
        System.out.println(vent.getEra());
        System.out.println(vent.costI().getCoins());
	    
	    
	}

	

}

