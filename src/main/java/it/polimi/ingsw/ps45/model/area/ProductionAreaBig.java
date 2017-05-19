package it.polimi.ingsw.ps45.model.area;

import it.polimi.ingsw.ps45.model.actions.state.NoActionState;
import it.polimi.ingsw.ps45.model.actions.state.ProductionState;
import it.polimi.ingsw.ps45.model.player.Player;

public class ProductionAreaBig extends ProductionArea {

	private static int bigProductionPenalty = 3;
	private static int COST = 1;
	private static int OCCUPANTS = 999;
	
	public ProductionAreaBig(){
		maxOccupants = OCCUPANTS;
		
		this.setCost(COST);
	}

	@Override
	public void immediateEffect(Player p, int value) {
		p.getActionBuilder().setState(new ProductionState(value - bigProductionPenalty));
	}




	
}
