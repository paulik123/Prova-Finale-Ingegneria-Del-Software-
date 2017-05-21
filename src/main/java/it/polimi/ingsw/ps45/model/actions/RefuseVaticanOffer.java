package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.player.Player;
import it.polimi.ingsw.ps45.model.vatican.ExcommunicationCard;

public class RefuseVaticanOffer implements Action{

	Player p;
	ExcommunicationCard c;
	
	RefuseVaticanOffer(Player p, ExcommunicationCard c){
		this.p = p;
		this.c = c;
	}
	@Override
	public void run() {
		c.immediateEffect(p, 0);
		p.setAnsweredVatican(true);
	}

}
