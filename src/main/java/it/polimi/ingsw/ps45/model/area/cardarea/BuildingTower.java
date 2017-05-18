package it.polimi.ingsw.ps45.model.area.cardarea;

import it.polimi.ingsw.ps45.model.player.Player;

public class BuildingTower implements Tower{
	
	public BuildingTower(){
		b0 = new BuildingCardAreaGroundFloor();
		b1 = new BuildingCardAreaFirstFloor();
		b2 = new BuildingCardAreaSecondFloor();
		b3 = new BuildingCardAreaThirdFloor();
	}
	
	private BuildingCardAreaGroundFloor b0;
	private BuildingCardAreaFirstFloor b1;
	private BuildingCardAreaSecondFloor b2;
	private BuildingCardAreaThirdFloor b3;
	
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

}
