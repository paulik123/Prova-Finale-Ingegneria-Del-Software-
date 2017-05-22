package it.polimi.ingsw.ps45;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import it.polimi.ingsw.ps45.model.cards.Era;
import it.polimi.ingsw.ps45.model.cards.Venture;
import it.polimi.ingsw.ps45.model.effects.CollectEffect;
import it.polimi.ingsw.ps45.model.effects.CouncilPrivilegeOneEffect;
import it.polimi.ingsw.ps45.model.effects.CouncilPrivilegeTwoEffect;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;

public class SerializeVenture {
	
	public static void main(String[] args){
		ConsumableSet costOne = new ConsumableSet();
		costOne.setStone(6);
		
		ConsumableSet costTwo = new ConsumableSet();
		costTwo.setStone(6);
	
		
		ConsumableSet reqOne = new ConsumableSet();
		reqOne.setStone(6);
		
		ConsumableSet reqTwo = new ConsumableSet();
		reqTwo.setStone(6);
		
		
		ConsumableSet immCons = new ConsumableSet();
		immCons.setMilitaryPoins(4);
		
		ConsumableSet endCons = new ConsumableSet();
		endCons.setVictoryPoints(4);
		
		
		CollectEffect immediateEff = new CollectEffect(immCons);
		CollectEffect endGameEff = new CollectEffect(endCons);
		CouncilPrivilegeOneEffect cpeffect = new CouncilPrivilegeOneEffect();
		//CouncilPrivilegeTwoEffect cpeffect = new CouncilPrivilegeTwoEffect();

		

	    Venture venture = new Venture(Era.III, "Costruire le Torri", costOne, costTwo, reqOne, reqTwo);
	    venture.addEffect(immediateEff);
	    venture.addEffect(cpeffect);
	    venture.addEndGameEffect(endGameEff);
	    try {
	         FileOutputStream fileOut = new FileOutputStream("Costruire le Torri.ser");
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

