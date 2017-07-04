package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.player.Player;
import it.polimi.ingsw.ps45.model.vatican.ExcommunicationCard;

/**
 * The actual actions that is executed by the player to refuse vatican at the end of each era.
 */
public class RefuseVaticanOffer implements Action{

	Player p;
	ExcommunicationCard c;
	
	/**
 	 * Constructor
 	 * @param p the player which executes the action.
	 * @param  c the ExcommunicationCard of which effect's the player will "suffer".
	 */
	RefuseVaticanOffer(Player p, ExcommunicationCard c){
		this.p = p;
		this.c = c;
	}
	
	/**
 	 * The method that runs the action.
	 */
	@Override
	public void run() {
		c.immediateEffect(p, 0);
		p.setAnsweredVatican(true);
		p.setVaticanPenalty(c.getEra());
	}

}
