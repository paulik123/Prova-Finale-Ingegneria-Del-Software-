package it.polimi.ingsw.ps45.model.area;

import it.polimi.ingsw.ps45.model.actions.state.HarvestState;
import it.polimi.ingsw.ps45.model.actions.state.NoActionState;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

public class MilitaryAndCoinArea extends NoCardArea{

	private static int COST = 1;
	private static int OCCUPANTS = 1;
	private static int MILITARYREWARD = 3;
	private static int COINSREWARD = 2;
	
	public MilitaryAndCoinArea(){
		
		maxOccupants = OCCUPANTS;
		

		this.setCost(COST);
	}

	@Override
	public void immediateEffect(Player p, int value) {
		ConsumableSet cs = new ConsumableSet();
		cs.setMilitaryPoins(MILITARYREWARD);
		cs.setCoins(COINSREWARD);
		
		p.getResourceSet().collect(cs);
		p.getActionBuilder().setState(new NoActionState());
	}

}
