package it.polimi.ingsw.ps45.model.area.cardarea;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import it.polimi.ingsw.ps45.model.player.Player;

public class TerritoryTower implements Tower{
	
	public TerritoryTower(){
		t0 = loadFromFile("TerritoryCardAreaGroundFloor.ser");
		t1 = loadFromFile("TerritoryCardAreaFirstFloor.ser");
		t2 = loadFromFile("TerritoryCardAreaSecondFloor.ser");
		t3 = loadFromFile("TerritoryCardAreaThirdFloor.ser");
		
	}
	
	private TerritoryCardArea t0;
	private TerritoryCardArea t1;
	private TerritoryCardArea t2;
	private TerritoryCardArea t3;
	
	@Override
	public boolean isOccupied() {
		int occupants = t0.getOccupants().size() + t1.getOccupants().size() + t2.getOccupants().size() + t3.getOccupants().size(); 
		if(occupants > 0)return true;
		return false;
	}

	@Override
	public boolean isOccupiedByPlayer(Player p) {
		return 	    t0.isOccupiedByPlayerWithColoredPawn(p) || 
					t1.isOccupiedByPlayerWithColoredPawn(p) || 
					t2.isOccupiedByPlayerWithColoredPawn(p) || 
					t3.isOccupiedByPlayerWithColoredPawn(p);
	}
	
	public TerritoryCardArea loadFromFile(String name){
		TerritoryCardArea x = null;
        try {
	         FileInputStream fileIn = new FileInputStream("serialized//areas//" + name);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         x = (TerritoryCardArea) in.readObject();
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
