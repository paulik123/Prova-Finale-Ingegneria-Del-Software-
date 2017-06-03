package it.polimi.ingsw.ps45;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.polimi.ingsw.ps45.gson.PropertyBasedInterfaceMarshal;
import it.polimi.ingsw.ps45.model.cards.Era;
import it.polimi.ingsw.ps45.model.cards.Venture;
import it.polimi.ingsw.ps45.model.effects.CollectEffect;
import it.polimi.ingsw.ps45.model.effects.CouncilPrivilegeOneEffect;
import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.effects.TakeAnyCardEffect;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;

public class SerializeVenture {
	
	public static void main(String[] args) throws IOException{
		ConsumableSet costOne = new ConsumableSet();
		costOne.setCoins(3);
		costOne.setWood(3);
		costOne.setStone(3);
		
		ConsumableSet costTwo = new ConsumableSet();
		costTwo.setCoins(3);
		costTwo.setWood(3);
		costTwo.setStone(3);
	
		
		ConsumableSet reqOne = new ConsumableSet();
		reqOne.setCoins(3);
		reqOne.setWood(3);
		reqOne.setStone(3);
		
		ConsumableSet reqTwo = new ConsumableSet();
		reqTwo.setCoins(3);
		reqTwo.setWood(3);
		reqTwo.setStone(3);
		
		ConsumableSet immCons = new ConsumableSet();
		immCons.setFaithPoints(1);

		
		ConsumableSet endCons = new ConsumableSet();
		endCons.setVictoryPoints(5);
		
		CollectEffect immediateEff = new CollectEffect(immCons);
		CollectEffect endGameEff = new CollectEffect(endCons);
		CouncilPrivilegeOneEffect cpeffect = new CouncilPrivilegeOneEffect();
		//CouncilPrivilegeTwoEffect cpeffect = new CouncilPrivilegeTwoEffect();
		//HarvestWithValueEffect harvestWithValueEff = new HarvestWithValueEffect(4);
		
		//ProductionWithValueEffect productionWithValueEff = new ProductionWithValueEffect(3);
		
		TakeAnyCardEffect takeAnyCardEffect = new TakeAnyCardEffect(7, new ConsumableSet());
		
		//TakeAnyCardEffect takeEffect = new TakeAnyCardEffect(7, new ConsumableSet());
		
	    Venture venture = new Venture(Era.III, "Riparare la Cattedrale", costOne, costTwo, reqOne, reqTwo);
	    venture.addEffect(takeAnyCardEffect);
	    venture.addEffect(immediateEff);
	    //venture.addEffect(productionWithValueEff);
	    //venture.addEffect(harvestWithValueEff);
	    //venture.addEffect(cpeffect);
	    venture.addEndGameEffect(endGameEff);
	    
        Writer writer = new FileWriter("Riparare la Cattedrale.json");

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Effect.class,
                        new PropertyBasedInterfaceMarshal()).create();
        gson.toJson(venture, writer);

        writer.close();
        
        Venture vent = gson.fromJson(new FileReader("Riparare la Cattedrale.json"), Venture.class);
        System.out.println(vent.getName());
        System.out.println(vent.getEra());
        System.out.println(vent.costI().getCoins());
	    
	    
	}

	

}

