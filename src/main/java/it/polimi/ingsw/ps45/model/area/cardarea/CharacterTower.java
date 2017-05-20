package it.polimi.ingsw.ps45.model.area.cardarea;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import it.polimi.ingsw.ps45.model.player.Player;

public class CharacterTower implements Tower{
	
	public CharacterTower(){
		c0 = loadFromFile("CharacterCardAreaGroundFloor.ser");
		c1 = loadFromFile("CharacterCardAreaFirstFloor.ser");
		c2 = loadFromFile("CharacterCardAreaSecondFloor.ser");
		c3 = loadFromFile("CharacterCardAreaThirdFloor.ser");
		
	}
	
	private CharacterCardArea c0;
	private CharacterCardArea c1;
	private CharacterCardArea c2;
	private CharacterCardArea c3;
	
	@Override
	public boolean isOccupied() {
		int occupants = c0.getOccupants().size() + c1.getOccupants().size() + c2.getOccupants().size() + c3.getOccupants().size(); 
		if(occupants > 0)return true;
		return false;
	}

	@Override
	public boolean isOccupiedByPlayer(Player p) {
		return 	    c0.isOccupiedByPlayerWithColoredPawn(p) || 
					c1.isOccupiedByPlayerWithColoredPawn(p) || 
					c2.isOccupiedByPlayerWithColoredPawn(p) || 
					c3.isOccupiedByPlayerWithColoredPawn(p);
	}
	
	public CharacterCardArea loadFromFile(String name){
		CharacterCardArea x = null;
        try {
	         FileInputStream fileIn = new FileInputStream("serialized//areas//" + name);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         x = (CharacterCardArea) in.readObject();
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
