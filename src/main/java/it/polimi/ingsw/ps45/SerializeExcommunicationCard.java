package it.polimi.ingsw.ps45;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.polimi.ingsw.ps45.gson.PropertyBasedInterfaceMarshal;
import it.polimi.ingsw.ps45.model.area.cardarea.VentureCardArea;
import it.polimi.ingsw.ps45.model.cards.Era;
import it.polimi.ingsw.ps45.model.cards.Territory;
import it.polimi.ingsw.ps45.model.effects.AFifthVictoryPointsPenaltyEffect;
import it.polimi.ingsw.ps45.model.effects.BuildingCostVictoryPointsEffect;
import it.polimi.ingsw.ps45.model.effects.CantPlacePawnOnMarkeyEffect;
import it.polimi.ingsw.ps45.model.effects.CollectEffect;
import it.polimi.ingsw.ps45.model.effects.CollectPenaltyEffect;
import it.polimi.ingsw.ps45.model.effects.ColoredPawnPenaltyEffect;
import it.polimi.ingsw.ps45.model.effects.CouncilPrivilegeOneEffect;
import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.effects.HarvestValuePenaltyEffect;
import it.polimi.ingsw.ps45.model.effects.MilitaryVictoryPointsPenaltyEffect;
import it.polimi.ingsw.ps45.model.effects.NoCharacterVictoryPointsEffect;
import it.polimi.ingsw.ps45.model.effects.NoEffect;
import it.polimi.ingsw.ps45.model.effects.NoFirstTurnEffect;
import it.polimi.ingsw.ps45.model.effects.NoTerritoryVictoryPointsEffect;
import it.polimi.ingsw.ps45.model.effects.NoVentureVictoryPointsEffect;
import it.polimi.ingsw.ps45.model.effects.ProductionValuePenaltyEffect;
import it.polimi.ingsw.ps45.model.effects.ResourceVictoryPointsPenaltyEffect;
import it.polimi.ingsw.ps45.model.effects.ServantAddingPenaltyEffect;
import it.polimi.ingsw.ps45.model.effects.TakeBuildingActionModifierEffect;
import it.polimi.ingsw.ps45.model.effects.TakeCharacterActionModifierEffect;
import it.polimi.ingsw.ps45.model.effects.TakeTerritoryActionModifierEffect;
import it.polimi.ingsw.ps45.model.effects.TakeVentureActionModifierEffect;
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
