package it.polimi.ingsw.ps45.model.area;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

public class CoinsMarketArea extends NoCardArea{

	private static int COST = 1;
	private static int OCCUPANTS = 1;
	private static int COINSREWARD = 5;
	
	public CoinsMarketArea(){
		
		maxOccupants = OCCUPANTS;
		

		this.setCost(COST);
	}

	@Override
	public void immediateEffect(Player p, int value) {
		ConsumableSet cs = new ConsumableSet();
		cs.setCoins(COINSREWARD);
		
		p.getResourceSet().collect(cs);
	}

}