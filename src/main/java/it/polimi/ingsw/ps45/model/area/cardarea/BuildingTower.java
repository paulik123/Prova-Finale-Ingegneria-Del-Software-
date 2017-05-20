package it.polimi.ingsw.ps45.model.area.cardarea;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import it.polimi.ingsw.ps45.model.player.Player;

public class BuildingTower implements Tower{
	
	public BuildingTower(){
		b0 = loadFromFile("BuildingCardAreaGroundFloor.ser");
		b1 = loadFromFile("BuildingCardAreaFirstFloor.ser");
		b2 = loadFromFile("BuildingCardAreaSecondFloor.ser");
		b3 = loadFromFile("BuildingCardAreaThirdFloor.ser");
	}
	
	private BuildingCardArea b0;
	private BuildingCardArea b1;
	private BuildingCardArea b2;
	private BuildingCardArea b3;
	
	@Override
	public boolean isOccupied() {
		int occupants = b0.getOccupants().size() + b1.getOccupants().size() + b2.getOccupants().size() + b3.getOccupants().size(); 
		if(occupants > 0)return true;
		return false;
	}

	@Override
	public boolean isOccupiedByPlayer(Player p) {
		return 	    b0.isOccupiedByPlayerWithColoredPawn(p) || 
					b1.isOccupiedByPlayerWithColoredPawn(p) || 
					b2.isOccupiedByPlayerWithColoredPawn(p) || 
					b3.isOccupiedByPlayerWithColoredPawn(p);
	}
	
	public BuildingCardArea loadFromFile(String name){
		BuildingCardArea x = null;
        try {
	         FileInputStream fileIn = new FileInputStream("serialized//areas//" + name);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         x = (BuildingCardArea) in.readObject();
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
