package it.polimi.ingsw.ps45.model.area.cardarea;

import it.polimi.ingsw.ps45.model.player.Player;

public class CharacterTower implements Tower{
	
	public CharacterTower(){
		c0 = new CharacterCardAreaGroundFloor();
		c1 = new CharacterCardAreaFirstFloor();
		c2 = new CharacterCardAreaSecondFloor();
		c3 = new CharacterCardAreaThirdFloor();
		
	}
	
	private CharacterCardAreaGroundFloor c0;
	private CharacterCardAreaFirstFloor c1;
	private CharacterCardAreaSecondFloor c2;
	private CharacterCardAreaThirdFloor c3;
	
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

}
