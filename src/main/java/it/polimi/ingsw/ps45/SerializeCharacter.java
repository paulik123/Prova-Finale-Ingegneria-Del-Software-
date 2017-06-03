package it.polimi.ingsw.ps45;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.polimi.ingsw.ps45.gson.PropertyBasedInterfaceMarshal;
import it.polimi.ingsw.ps45.model.cards.Character;
import it.polimi.ingsw.ps45.model.cards.Era;
import it.polimi.ingsw.ps45.model.effects.CollectEffect;
import it.polimi.ingsw.ps45.model.effects.ConsumableSetPerBuildingEffect;
import it.polimi.ingsw.ps45.model.effects.CouncilPrivilegeOneEffect;
import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.effects.NoCardAreaCollectEffect;
import it.polimi.ingsw.ps45.model.effects.TakeAnyCardEffect;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;

public class SerializeCharacter {

	public static void main( String[] args ) throws IOException
    {
		String name = "Governatore";
		
    	ConsumableSet cost = new ConsumableSet();
    	cost.setWood(0);
    	cost.setStone(0);
    	cost.setCoins(6);
    	cost.setMilitaryPoins(0);
    	cost.setServants(0);
    	cost.setFaithPoints(0);
    	
    	ConsumableSet immediate = new ConsumableSet();
    	immediate.setWood(0);
    	immediate.setStone(0);
    	immediate.setCoins(0);
    	immediate.setMilitaryPoins(0);
    	immediate.setServants(0);
    	immediate.setFaithPoints(2);
    	
    	ConsumableSet discount = new ConsumableSet();
    	discount.setWood(0);
    	discount.setStone(0);
    	discount.setCoins(0);
    	discount.setMilitaryPoins(0);
    	discount.setServants(0);
    	discount.setFaithPoints(0);
    	
    	ConsumableSet reward = new ConsumableSet();
    	reward.setWood(0);
    	reward.setStone(0);
    	reward.setCoins(0);
    	reward.setMilitaryPoins(0);
    	reward.setServants(0);
    	reward.setFaithPoints(0);
    	reward.setVictoryPoints(2);
    	
    	TakeAnyCardEffect tankeeffect = new TakeAnyCardEffect(7, discount);
    	//TakeCharacterEffect discountEffect = new TakeBuildingResourceDiscountEffect(discount);
    	ConsumableSetPerBuildingEffect perEffect = new ConsumableSetPerBuildingEffect(reward);

        CollectEffect collectEff = new CollectEffect(immediate);
        
        NoCardAreaCollectEffect noCardEffect = new NoCardAreaCollectEffect();
        
        
        CouncilPrivilegeOneEffect cpeffect = new CouncilPrivilegeOneEffect();

        Character c = new Character(Era.III, name, cost);
        c.addEffect(perEffect);
        //c.addEffect(cpeffect);


        Writer writer = new FileWriter(name+".json");

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Effect.class,
                        new PropertyBasedInterfaceMarshal()).create();
        gson.toJson(c, writer);

        writer.close();
    }
	
}
