package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.actions.CouncilPrivilege.CouncilPrivilege;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * The actual action that is executed by the player when he wants to activate a leader card.
 */
public class DiscardLeaderCardAction implements Action{
	
	private Player p;
	private int index;
	private CouncilPrivilege cp1;
	
	/**
 	 * Constructor
	 * @param  p the player to which the card leader's effects will be executed on.
	 * @param  index the index of the LeaderCard in the player's LeaderCard list.
	 */
	public DiscardLeaderCardAction(Player p, int index, CouncilPrivilege cp1){
		this.p = p;
		this.index = index;
		this.cp1 = cp1;
	}
	
	/**
 	 * The method that runs the action.
	 */
	@Override
	public void run() {
		p.getResourceSet().getLeaderCardList().remove(index);
		p.getResourceSet().collect(cp1.getConsumableSet());	
	}

}
