package it.polimi.ingsw.ps45.model.area.cardarea;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

public class TerritoryCardAreaSecondFloor extends TerritoryCardArea{
	
	private static int OCCUPANTS = 1;
	private static int COST = 5;
	private static int WOODREWARD = 1;
	
	public TerritoryCardAreaSecondFloor(){
		this.setMaxOccupants(OCCUPANTS);
		this.setCost(COST);
	}

	@Override
	public void immediateEffect(Player p) {
		ConsumableSet cs = new ConsumableSet();
		cs.setWood(WOODREWARD);
		p.getResourceSet().collect(cs);
	}

}
