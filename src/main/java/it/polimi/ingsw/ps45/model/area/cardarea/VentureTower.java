package it.polimi.ingsw.ps45.model.area.cardarea;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import it.polimi.ingsw.ps45.model.player.Player;

public class VentureTower implements Tower{
	
	public VentureTower(){
		v0 = loadFromFile("VentureCardAreaGroundFloor.ser");
		v1 = loadFromFile("VentureCardAreaFirstFloor.ser");
		v2 = loadFromFile("VentureCardAreaSecondFloor.ser");
		v3 = loadFromFile("VentureCardAreaThirddFloor.ser");
		
	}
	
	private VentureCardArea v0;
	private VentureCardArea v1;
	private VentureCardArea v2;
	private VentureCardArea v3;
	
	@Override
	public boolean isOccupied() {
		int occupants = v0.getOccupants().size() + v1.getOccupants().size() + v2.getOccupants().size() + v3.getOccupants().size(); 
		if(occupants > 0)return true;
		return false;
	}

	@Override
	public boolean isOccupiedByPlayer(Player p) {
		return 	    v0.isOccupiedByPlayerWithColoredPawn(p) || 
					v1.isOccupiedByPlayerWithColoredPawn(p) || 
					v2.isOccupiedByPlayerWithColoredPawn(p) || 
					v3.isOccupiedByPlayerWithColoredPawn(p);
	}
	
	public VentureCardArea loadFromFile(String name){
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
