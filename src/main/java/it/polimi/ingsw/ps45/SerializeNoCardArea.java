package it.polimi.ingsw.ps45;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import it.polimi.ingsw.ps45.model.area.Board;
import it.polimi.ingsw.ps45.model.area.NoCardArea;
import it.polimi.ingsw.ps45.model.cards.Era;
import it.polimi.ingsw.ps45.model.cards.Territory;
import it.polimi.ingsw.ps45.model.effects.CollectEffect;
import it.polimi.ingsw.ps45.model.effects.CouncilPrivilegeOneAndCollectEffect;
import it.polimi.ingsw.ps45.model.effects.CouncilPrivilegeTwoEffect;
import it.polimi.ingsw.ps45.model.effects.HarvestEffect;
import it.polimi.ingsw.ps45.model.effects.ProductionEffect;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

public class SerializeNoCardArea {
	public static void main(String[] args){
		
		ConsumableSet cs = new ConsumableSet();
		cs.setCoins(1);
		CouncilPrivilegeTwoEffect e = new CouncilPrivilegeTwoEffect();
    	NoCardArea area = new NoCardArea(1,1, e);
		try {
	         FileOutputStream fileOut = new FileOutputStream("C://outout//.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(area);
	         out.close();
	         fileOut.close();
	      }catch(IOException i) {
	         i.printStackTrace();
	      }
	      
		
		/*
		NoCardArea x = null;
        try {
	         FileInputStream fileIn = new FileInputStream("C://outout//ProductionAreaBig.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         x = (NoCardArea) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i) {
	         i.printStackTrace();
	         return;
	      }catch(Exception ex) {
	         System.out.println("Employee class not found");
	         ex.printStackTrace();
	         return;
	      }
        System.out.println(x.getMaxOccupants());
        */
	}
}
