package it.polimi.ingsw.ps45.model.area.cardarea;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

public class BuildingCardAreaThirdFloor extends BuildingCardArea{
	
	private static int OCCUPANTS = 1;
	private static int COST = 7;
	private static int MILITARYREWARD = 2;
	
	public BuildingCardAreaThirdFloor(){
		this.setMaxOccupants(OCCUPANTS);
		this.setCost(COST);
	}

	@Override
	public void immediateEffect(Player p) {
		ConsumableSet cs = new ConsumableSet();
		cs.setMilitaryPoins(MILITARYREWARD);
		
		p.getResourceSet().collect(cs);
		
	}

}
