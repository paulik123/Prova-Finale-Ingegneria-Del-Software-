package it.polimi.ingsw.ps45;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import it.polimi.ingsw.ps45.model.area.cardarea.VentureCardArea;
import it.polimi.ingsw.ps45.model.cards.Era;
import it.polimi.ingsw.ps45.model.effects.AFifthVictoryPointsPenaltyEffect;
import it.polimi.ingsw.ps45.model.effects.BuildingCostVictoryPointsEffect;
import it.polimi.ingsw.ps45.model.effects.CantPlacePawnOnMarkeyEffect;
import it.polimi.ingsw.ps45.model.effects.CollectEffect;
import it.polimi.ingsw.ps45.model.effects.CollectPenaltyEffect;
import it.polimi.ingsw.ps45.model.effects.ColoredPawnPenaltyEffect;
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
	public static void main(String[] args){


		
		ResourceVictoryPointsPenaltyEffect effect = new ResourceVictoryPointsPenaltyEffect();

    	ExcommunicationCard card = new ExcommunicationCard(Era.III, effect);
		try {
	         FileOutputStream fileOut = new FileOutputStream("C://outout//excom//ExcommunicationResourceVictoryPointsPenalty.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(card);
	         out.close();
	         fileOut.close();
	      }catch(IOException i) {
	         i.printStackTrace();
	      }
	      
	}
}
