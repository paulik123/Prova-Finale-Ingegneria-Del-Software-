package it.polimi.ingsw.ps45.model.area.cardarea;

import it.polimi.ingsw.ps45.model.player.Player;

public class VentureCardAreaFirstFloor extends VentureCardArea{
	
	private static int OCCUPANTS = 1;
	private static int COST = 3;
	
	public VentureCardAreaFirstFloor(){
		this.setMaxOccupants(OCCUPANTS);
		this.setCost(COST);
	}

	@Override
	public void immediateEffect(Player p) {
		
		
	}

}
