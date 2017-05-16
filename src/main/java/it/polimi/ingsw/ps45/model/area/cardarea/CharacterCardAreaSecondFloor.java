package it.polimi.ingsw.ps45.model.area.cardarea;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

public class CharacterCardAreaSecondFloor extends CharacterCardArea{
	
	private static int OCCUPANTS = 1;
	private static int COST = 5;
	private static int STONEREWARD = 1;
	
	public CharacterCardAreaSecondFloor(){
		this.setMaxOccupants(OCCUPANTS);
		this.setCost(COST);
	}

	@Override
	public void immediateEffect(Player p) {
		ConsumableSet cs = new ConsumableSet();
		cs.setWood(STONEREWARD);
		
		p.getResourceSet().collect(cs);
	}

}
