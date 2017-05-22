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
		costOne.setMilitaryPoins(3);
		
		ConsumableSet costTwo = new ConsumableSet();
		costTwo.setMilitaryPoins(3);
		
		ConsumableSet reqOne = new ConsumableSet();
		reqOne.setMilitaryPoins(5);
		
		ConsumableSet reqTwo = new ConsumableSet();
		reqTwo.setMilitaryPoins(5);
		
		
		ConsumableSet immCons = new ConsumableSet();
		immCons.setFaithPoints(2);
	
		
		ConsumableSet endCons = new ConsumableSet();
		endCons.setVictoryPoints(5);
		
		
		CollectEffect immediateEff = new CollectEffect(immCons);
		CollectEffect endGameEff = new CollectEffect(endCons);
		CouncilPrivilegeOneEffect cpeffect = new CouncilPrivilegeOneEffect();
		//CouncilPrivilegeTwoEffect cpeffect = new CouncilPrivilegeTwoEffect();

		

	    Venture venture = new Venture(Era.I, "Combattere le Eresie", costOne, costTwo, reqOne, reqTwo);
	    venture.addEffect(immediateEff);
	    //venture.addEffect(cpeffect);
	    venture.addEndGameEffect(endGameEff);
	    try {
	         FileOutputStream fileOut = new FileOutputStream("Combattere le Eresie.ser");
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

