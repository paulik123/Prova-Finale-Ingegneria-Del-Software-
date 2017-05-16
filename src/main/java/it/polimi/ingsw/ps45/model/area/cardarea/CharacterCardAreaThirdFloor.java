package it.polimi.ingsw.ps45.model.area.cardarea;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

public class CharacterCardAreaThirdFloor extends CharacterCardArea{
	
	private static int OCCUPANTS = 1;
	private static int COST = 7;
	private static int STONEREWARD = 2;
	
	public CharacterCardAreaThirdFloor(){
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
