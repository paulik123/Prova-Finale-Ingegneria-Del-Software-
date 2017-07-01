package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.player.Player;

/**
 * The actual action that is executed by the player when he wants to use a leader card's per turn effect.
 */
public class UseLeaderCardAction implements Action{

	private Player p;
	private int index;
	
	/**
 	 * Constructor
	 * @param  p the player to which the card leader's effects will be executed on.
	 * @param  index the index of the LeaderCard in the player's LeaderCard list.
	 */
	public UseLeaderCardAction(Player p, int index){
		this.p = p;
		this.index = index;
	}
	
	/**
 	 * The method that runs the action.
	 */
	@Override
	public void run() {
		p.getResourceSet().getActivatedLeaderCardList().get(index).perRoundEffect(p);
	}

}
