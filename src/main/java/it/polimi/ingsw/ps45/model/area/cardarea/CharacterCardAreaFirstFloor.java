package it.polimi.ingsw.ps45.model.area.cardarea;

import it.polimi.ingsw.ps45.model.player.Player;

public class CharacterCardAreaFirstFloor extends CharacterCardArea{
	
	private static int OCCUPANTS = 1;
	private static int COST = 3;
	
	public CharacterCardAreaFirstFloor(){
		this.setMaxOccupants(OCCUPANTS);
		this.setCost(COST);
	}

	@Override
	public void immediateEffect(Player p, int value) {
		
		
	}

}