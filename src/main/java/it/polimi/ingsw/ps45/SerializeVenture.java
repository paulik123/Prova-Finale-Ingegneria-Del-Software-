package it.polimi.ingsw.ps45;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import it.polimi.ingsw.ps45.model.actions.state.AddServantsToHarvestState;
import it.polimi.ingsw.ps45.model.cards.Era;
import it.polimi.ingsw.ps45.model.cards.Venture;
import it.polimi.ingsw.ps45.model.effects.CollectEffect;
import it.polimi.ingsw.ps45.model.effects.CouncilPrivilegeOneEffect;
import it.polimi.ingsw.ps45.model.effects.CouncilPrivilegeTwoEffect;
import it.polimi.ingsw.ps45.model.effects.HarvestWithValueEffect;
import it.polimi.ingsw.ps45.model.effects.ProductionWithValueEffect;
import it.polimi.ingsw.ps45.model.effects.TakeAnyCardEffect;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;

public class SerializeVenture {
	
	public static void main(String[] args){
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
		immCons.setMilitaryPoins(4);
		
		ConsumableSet endCons = new ConsumableSet();
		endCons.setVictoryPoints(5);
		
		
		CollectEffect immediateEff = new CollectEffect(immCons);
		CollectEffect endGameEff = new CollectEffect(endCons);
		CouncilPrivilegeOneEffect cpeffect = new CouncilPrivilegeOneEffect();
		//CouncilPrivilegeTwoEffect cpeffect = new CouncilPrivilegeTwoEffect();
		
		TakeAnyCardEffect takeEffect = new TakeAnyCardEffect(7, new ConsumableSet());

		

	    Venture venture = new Venture(Era.III, "Riparare la Catedrale", costOne, costTwo, reqOne, reqTwo);
	    venture.addEffect(takeEffect);
	    venture.addEndGameEffect(endGameEff);
	    try {
	         FileOutputStream fileOut = new FileOutputStream("Riparare la Catedrale.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(venture);
	         out.close();
	         fileOut.close();
	    }
	    catch(IOException i) {
	         i.printStackTrace();
	      }
	}

	

}

