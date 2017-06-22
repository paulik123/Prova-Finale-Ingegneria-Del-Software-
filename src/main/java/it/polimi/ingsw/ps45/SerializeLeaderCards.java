package it.polimi.ingsw.ps45;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.google.gson.Gson;

import it.polimi.ingsw.ps45.gson.GsonWithInterface;
import it.polimi.ingsw.ps45.model.cards.LeaderCard;
import it.polimi.ingsw.ps45.model.effects.CanPlacePawnOnOccupiedAreaEffect;
import it.polimi.ingsw.ps45.model.effects.CollectEffect;
import it.polimi.ingsw.ps45.model.effects.ColoredPawnDefaultValueEffect;
import it.polimi.ingsw.ps45.model.effects.ColoredPawnPenaltyEffect;
import it.polimi.ingsw.ps45.model.effects.CouncilPrivilegeOneEffect;
import it.polimi.ingsw.ps45.model.effects.HarvestEffect;
import it.polimi.ingsw.ps45.model.effects.LorenzoDeMediciEffect;
import it.polimi.ingsw.ps45.model.effects.NeutralPawnDefaultValue;
import it.polimi.ingsw.ps45.model.effects.NoTerritoryMilitaryPointsRequirementsEffect;
import it.polimi.ingsw.ps45.model.effects.NoTowerCoinsPenaltyEffect;
import it.polimi.ingsw.ps45.model.effects.ProductionEffect;
import it.polimi.ingsw.ps45.model.effects.SistoEffect;
import it.polimi.ingsw.ps45.model.effects.TakeAnyCardEffect;
import it.polimi.ingsw.ps45.model.effects.TakeConsumablesTwoTimesEffect;
import it.polimi.ingsw.ps45.model.effects.ThreeCoinsCardDiscountEffect;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;

public class SerializeLeaderCards {
	
	public static void main(String[] args){
	
		
		ConsumableSet cs = new ConsumableSet();
		cs.setWood(0);
		cs.setStone(0);
		cs.setCoins(0);
		cs.setServants(0);
		cs.setMilitaryPoins(0);
		cs.setFaithPoints(0);
		cs.setVictoryPoints(0);
		
		int tCost = 0;
		int cCost = 0;
		int bCost = 0;
		int vCost = 5;
		int anyCost = 0;
		
		String name = "Francesco Sforza";
		
		
		//ProductionEffect immediateEffect = new LorenzoDeMediciEffect();
		HarvestEffect perRoundEffect = new HarvestEffect(1);
		
		LeaderCard lc = new LeaderCard(name, cs, tCost, cCost, bCost, vCost, anyCost);
		
		//lc.addImmediateEffect(immediateEffect);
		lc.addPerRoundEffect(perRoundEffect);
		
		Writer writer;
		try {
			writer = new FileWriter("serialized\\cards\\leader\\" + name + ".json");
	        Gson gson = GsonWithInterface.getGson();
	        gson.toJson(lc, writer);

	        writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
