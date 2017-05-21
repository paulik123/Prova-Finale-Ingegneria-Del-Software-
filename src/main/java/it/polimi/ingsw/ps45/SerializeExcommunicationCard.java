package it.polimi.ingsw.ps45;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import it.polimi.ingsw.ps45.model.area.cardarea.VentureCardArea;
import it.polimi.ingsw.ps45.model.cards.Era;
import it.polimi.ingsw.ps45.model.effects.CollectEffect;
import it.polimi.ingsw.ps45.model.effects.CollectPenaltyEffect;
import it.polimi.ingsw.ps45.model.effects.ColoredPawnPenaltyEffect;
import it.polimi.ingsw.ps45.model.effects.HarvestValuePenaltyEffect;
import it.polimi.ingsw.ps45.model.effects.NoEffect;
import it.polimi.ingsw.ps45.model.effects.ProductionValuePenaltyEffect;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.vatican.ExcommunicationCard;

public class SerializeExcommunicationCard {
	public static void main(String[] args){


		
		ColoredPawnPenaltyEffect effect = new ColoredPawnPenaltyEffect(-3);

    	ExcommunicationCard card = new ExcommunicationCard(Era.I, effect);
		try {
	         FileOutputStream fileOut = new FileOutputStream("C://outout//excom//ExcommunicationColoredPawnPenalty.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(card);
	         out.close();
	         fileOut.close();
	      }catch(IOException i) {
	         i.printStackTrace();
	      }
	      
	}
}
