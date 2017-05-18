package it.polimi.ingsw.ps45.model.area.cardarea;

import it.polimi.ingsw.ps45.model.player.Player;

public class VentureTower implements Tower{
	
	public VentureTower(){
		v0 = new VentureCardAreaGroundFloor();
		v1 = new VentureCardAreaFirstFloor();
		v2 = new VentureCardAreaSecondFloor();
		v3 = new VentureCardAreaThirdFloor();
		
	}
	
	private VentureCardAreaGroundFloor v0;
	private VentureCardAreaFirstFloor v1;
	private VentureCardAreaSecondFloor v2;
	private VentureCardAreaThirdFloor v3;
	
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

}
