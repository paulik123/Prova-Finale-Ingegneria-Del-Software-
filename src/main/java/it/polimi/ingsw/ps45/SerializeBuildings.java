package it.polimi.ingsw.ps45;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.polimi.ingsw.ps45.gson.PropertyBasedInterfaceMarshal;
import it.polimi.ingsw.ps45.model.cards.Era;
import it.polimi.ingsw.ps45.model.cards.Building;
import it.polimi.ingsw.ps45.model.cards.Character;
import it.polimi.ingsw.ps45.model.effects.CollectEffect;
import it.polimi.ingsw.ps45.model.effects.ConsumableSetPerBuildingEffect;
import it.polimi.ingsw.ps45.model.effects.ConsumableSetPerCharactersEffect;
import it.polimi.ingsw.ps45.model.effects.ConsumableSetPerMilitaryPointsEffect;
import it.polimi.ingsw.ps45.model.effects.ConsumableSetPerTerritoryEffect;
import it.polimi.ingsw.ps45.model.effects.ConsumableSetPerVentureEffect;
import it.polimi.ingsw.ps45.model.effects.CouncilPrivilegeOneEffect;
import it.polimi.ingsw.ps45.model.effects.CouncilPrivilegeThreeEffect;
import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.effects.HarvestValuePenaltyEffect;
import it.polimi.ingsw.ps45.model.effects.HarvestWithValueEffect;
import it.polimi.ingsw.ps45.model.effects.NoCardAreaCollectEffect;
import it.polimi.ingsw.ps45.model.effects.ProductionExchangeConsumableSetCouncilPrivilegeEffect;
import it.polimi.ingsw.ps45.model.effects.ProductionExchangeEffect;
import it.polimi.ingsw.ps45.model.effects.ProductionValuePenaltyEffect;
import it.polimi.ingsw.ps45.model.effects.ProductionWithValueEffect;
import it.polimi.ingsw.ps45.model.effects.TakeAnyCardEffect;
import it.polimi.ingsw.ps45.model.effects.TakeBuildingActionModifierEffect;
import it.polimi.ingsw.ps45.model.effects.TakeBuildingEffect;
import it.polimi.ingsw.ps45.model.effects.TakeBuildingResourceDiscountEffect;
import it.polimi.ingsw.ps45.model.effects.TakeCharacterActionModifierEffect;
import it.polimi.ingsw.ps45.model.effects.TakeCharacterEffect;
import it.polimi.ingsw.ps45.model.effects.TakeCharacterResourceDiscountEffect;
import it.polimi.ingsw.ps45.model.effects.TakeTerritoryActionModifierEffect;
import it.polimi.ingsw.ps45.model.effects.TakeTerritoryEffect;
import it.polimi.ingsw.ps45.model.effects.TakeVentureActionModifierEffect;
import it.polimi.ingsw.ps45.model.effects.TakeVentureEffect;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;

public class SerializeBuildings {

	public static void main( String[] args ) throws IOException
    {
		String name = "Giardino";
		
    	ConsumableSet cost = new ConsumableSet();
    	cost.setWood(4);
    	cost.setStone(2);
    	cost.setCoins(0);
    	cost.setMilitaryPoins(0);
    	cost.setServants(2);
    	cost.setFaithPoints(0);
    	
    	ConsumableSet immediate = new ConsumableSet();
    	immediate.setWood(0);
    	immediate.setStone(0);
    	immediate.setCoins(0);
    	immediate.setMilitaryPoins(0);
    	immediate.setServants(0);
    	immediate.setFaithPoints(0);
    	immediate.setVictoryPoints(10);
    	
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
    	reward.setVictoryPoints(0);
    	//////
    	
    	ConsumableSet beforeI = new ConsumableSet();
    	beforeI.setWood(1);
    	beforeI.setStone(1);
    	beforeI.setCoins(0);
    	beforeI.setMilitaryPoins(0);
    	beforeI.setServants(0);
    	beforeI.setFaithPoints(0);
    	beforeI.setVictoryPoints(0);
    	
    	ConsumableSet afterI = new ConsumableSet();
    	afterI.setWood(0);
    	afterI.setStone(0);
    	afterI.setCoins(0);
    	afterI.setMilitaryPoins(0);
    	afterI.setServants(0);
    	afterI.setFaithPoints(2);
    	afterI.setVictoryPoints(0);
    	
    	ConsumableSet beforeII = new ConsumableSet();
    	beforeII.setWood(1);
    	beforeII.setStone(1);
    	beforeII.setCoins(0);
    	beforeII.setMilitaryPoins(0);
    	beforeII.setServants(0);
    	beforeII.setFaithPoints(0);
    	beforeII.setVictoryPoints(0);
    	
    	ConsumableSet afterII = new ConsumableSet();
    	afterII.setWood(0);
    	afterII.setStone(0);
    	afterII.setCoins(0);
    	afterII.setMilitaryPoins(0);
    	afterII.setServants(0);
    	afterII.setFaithPoints(0);
    	afterII.setVictoryPoints(3);
    	
    	int productionLevel = 1;
    	
    	CollectEffect collectEffect = new CollectEffect(immediate);
    	CollectEffect produtctionCollectEffect = new CollectEffect(afterII);
    	
    	ProductionExchangeEffect exchangeEffectI = new ProductionExchangeEffect(beforeI, afterI);
    	ProductionExchangeEffect exchangeEffectII = new ProductionExchangeEffect(beforeII, afterII);
    	ProductionExchangeConsumableSetCouncilPrivilegeEffect councilExchange = new ProductionExchangeConsumableSetCouncilPrivilegeEffect(beforeI);
    	ConsumableSetPerTerritoryEffect c = new ConsumableSetPerTerritoryEffect(afterII);
    	CouncilPrivilegeOneEffect cpe = new CouncilPrivilegeOneEffect();
    	

        Building b = new Building(Era.III, name, productionLevel, cost);
        b.addEffect(collectEffect);
        
        b.addProductionIEffect(produtctionCollectEffect);

        
        b.addProductionIIEffect(produtctionCollectEffect);




        Writer writer = new FileWriter(name+".json");

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Effect.class,
                        new PropertyBasedInterfaceMarshal()).create();
        gson.toJson(b, writer);

        writer.close();
    }
	
}
