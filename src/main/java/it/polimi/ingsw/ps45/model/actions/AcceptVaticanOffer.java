package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.actions.state.NoActionState;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;
import it.polimi.ingsw.ps45.model.vatican.VaticanVictoryPointsConverter;

/**
 * The actual actions that is executed by the player to accept vatican at the end of each era.
 */
public class AcceptVaticanOffer implements Action{

	private Player p;
	
	/**
 	 * Constructor
	 * @param  p the object of the player that executes the action.
	 */
	public AcceptVaticanOffer(Player p){
		this.p = p;
	}
	
	/**
 	 * The method that runs the action.
	 */
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
