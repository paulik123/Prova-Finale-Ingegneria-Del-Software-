package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.actions.state.NoActionState;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;
import it.polimi.ingsw.ps45.model.vatican.VaticanVictoryPointsConverter;

public class AcceptVaticanOffer implements Action{

	Player p;
	
	AcceptVaticanOffer(Player p){
		this.p = p;
	}
	@Override
	public void run() {
		VaticanVictoryPointsConverter cnv = new VaticanVictoryPointsConverter();
		int victoryPoints = cnv.getVictoryPoints(p.getResourceSet().getResources().getFaithPoints());
		
		if(p.getResourceSet().getPermanentEffects().isHasActivatedSisto()) victoryPoints = victoryPoints + 5;
		
		ConsumableSet cs = new ConsumableSet();
		cs.setVictoryPoints(victoryPoints);
		p.getResourceSet().collect(cs);
		
		p.getResourceSet().getResources().setFaithPoints(0);
		p.setAnsweredVatican(true);
	}

}
