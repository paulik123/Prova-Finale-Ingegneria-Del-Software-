package it.polimi.ingsw.ps45.model.area.cardarea;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

public class VentureCardAreaThirdFloor extends VentureCardArea{
	
	private static int OCCUPANTS = 1;
	private static int COST = 7;
	private static int COINSREWARD = 2;
	
	public VentureCardAreaThirdFloor(){
		this.setMaxOccupants(OCCUPANTS);
		this.setCost(COST);
	}

	@Override
	public void immediateEffect(Player p, int value) {
		ConsumableSet cs = new ConsumableSet();
		cs.setCoins(COINSREWARD);
		
		p.getResourceSet().collect(cs);
	}

}
