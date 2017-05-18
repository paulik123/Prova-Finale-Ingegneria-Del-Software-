package it.polimi.ingsw.ps45.model.area.cardarea;

import it.polimi.ingsw.ps45.model.player.Player;

public class TerritoryTower implements Tower{
	
	public TerritoryTower(){
		t0 = new TerritoryCardAreaGroundFloor();
		t1 = new TerritoryCardAreaFirstFloor();
		t2 = new TerritoryCardAreaSecondFloor();
		t3 = new TerritoryCardAreaThirdFloor();
		
	}
	
	private TerritoryCardAreaGroundFloor t0;
	private TerritoryCardAreaFirstFloor t1;
	private TerritoryCardAreaSecondFloor t2;
	private TerritoryCardAreaThirdFloor t3;
	
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

}
