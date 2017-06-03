package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.area.cardarea.BuildingCardArea;
import it.polimi.ingsw.ps45.model.cards.Building;
import it.polimi.ingsw.ps45.model.player.Player;

public class NoPawnBuildingAction implements Action{
	
	private Player p;
	private BuildingCardArea b;
	private int value;
	
	protected NoPawnBuildingAction(Player p, BuildingCardArea b){
		this.p = p;
		this.b = b;
		value = 0;
	}

	@Override
	public void run() {
		if(!p.getResourceSet().getPermanentEffects().isNoCardAreaEffect())b.immediateEffect(p,value);
		
		Building card = b.getBuilding();
		b.setBuilding(null);
		p.getResourceSet().getBuildingList().add(card);
		card.immediateEffect(p);
	}

}
