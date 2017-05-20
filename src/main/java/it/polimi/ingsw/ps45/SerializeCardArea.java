package it.polimi.ingsw.ps45;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import it.polimi.ingsw.ps45.model.area.NoCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.BuildingCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.CharacterCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.TerritoryCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.VentureCardArea;
import it.polimi.ingsw.ps45.model.effects.CollectEffect;
import it.polimi.ingsw.ps45.model.effects.CouncilPrivilegeTwoEffect;
import it.polimi.ingsw.ps45.model.effects.NoEffect;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;

public class SerializeCardArea {
public static void main(String[] args){
		
		ConsumableSet cs = new ConsumableSet();

		/*
		CollectEffect effect = new CollectEffect(cs);
		cs.setCoins(2);
		NoEffect noEffect = new NoEffect();
    	VentureCardArea area = new VentureCardArea(7, effect);
		try {
	         FileOutputStream fileOut = new FileOutputStream("C://outout//VentureCardAreaThirdFloor.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(area);
	         out.close();
	         fileOut.close();
	      }catch(IOException i) {
	         i.printStackTrace();
	      }
	      */
		System.out.println(loadFromFile("VentureCardAreaThirdFloor.ser").getCost());

	}

public static VentureCardArea loadFromFile(String name){
	VentureCardArea x = null;
    try {
         FileInputStream fileIn = new FileInputStream("serialized//areas//" + name);
         ObjectInputStream in = new ObjectInputStream(fileIn);
         x = (VentureCardArea) in.readObject();
         in.close();
         fileIn.close();
      }catch(IOException i) {
         i.printStackTrace();
      }catch(Exception ex) {
         ex.printStackTrace();
      }
    return x;
}
}
