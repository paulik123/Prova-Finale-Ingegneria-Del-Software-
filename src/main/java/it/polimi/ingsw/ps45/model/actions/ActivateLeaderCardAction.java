package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.player.Player;

public class ActivateLeaderCardAction implements Action{
	
	private Player p;
	private int index;
	
	public ActivateLeaderCardAction(Player p, int index){
		this.p = p;
		this.index = index;
	}
	

	@Override
	public void run() {
		p.getResourceSet().getLeaderCardList().get(index).immediateEffect(p);
		p.getResourceSet().activateLeaderCard(index);
	}

}
