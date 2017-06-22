package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.player.Player;

public class UseLeaderCardAction implements Action{

	private Player p;
	private int index;
	
	public UseLeaderCardAction(Player p, int index){
		this.p = p;
		this.index = index;
	}
	

	@Override
	public void run() {
		p.getResourceSet().getActivatedLeaderCardList().get(index).perRoundEffect(p);
	}

}
