package it.polimi.ingsw.ps45.model.area;

import it.polimi.ingsw.ps45.model.actions.state.CouncilPrivilegeOneState;
import it.polimi.ingsw.ps45.model.actions.state.HarvestState;
import it.polimi.ingsw.ps45.model.player.Player;

public class HarvestAreaBig extends HarvestArea {

	private static int bigHarvestPenalty = 3;
	private static int COST = 1;
	private static int OCCUPANTS = 999;
	
	public HarvestAreaBig(){
		maxOccupants = OCCUPANTS;
		
		this.setCost(COST);
	}

	@Override
	public void immediateEffect(Player p, int value) {
		p.getActionBuilder().setState(new HarvestState(value - bigHarvestPenalty));
	}




	
}
