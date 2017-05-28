package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.actions.state.NoActionState;
import it.polimi.ingsw.ps45.model.player.Player;

public class AcceptVaticanOffer implements Action{

	Player p;
	
	AcceptVaticanOffer(Player p){
		this.p = p;
	}
	@Override
	public void run() {
		p.getResourceSet().getResources().setFaithPoints(0);
		p.setAnsweredVatican(true);
		p.getActionBuilder().setState(new NoActionState());
	}

}
