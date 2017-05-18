package it.polimi.ingsw.ps45.model.area;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

public class ServantsMarketArea extends NoCardArea{

	private static int COST = 1;
	private static int OCCUPANTS = 1;
	private static int SERVANTSREWARD = 5;
	
	public ServantsMarketArea(){
		
		maxOccupants = OCCUPANTS;
		

		this.setCost(COST);
	}

	@Override
	public void immediateEffect(Player p) {
		ConsumableSet cs = new ConsumableSet();
		cs.setServants(SERVANTSREWARD);
		
		p.getResourceSet().collect(cs);
	}

}
