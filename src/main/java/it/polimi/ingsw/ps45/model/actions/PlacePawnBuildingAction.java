package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.area.cardarea.BuildingCardArea;
import it.polimi.ingsw.ps45.model.cards.Building;
import it.polimi.ingsw.ps45.model.player.PawnType;
import it.polimi.ingsw.ps45.model.player.Player;

public class PlacePawnBuildingAction implements Action{
	
	private Player p;
	private BuildingCardArea b;
	private PawnType pt;
	private int value;
	
	protected PlacePawnBuildingAction(Player p, BuildingCardArea b, PawnType pt, int value){
		this.p = p;
		this.b = b;
		this.pt = pt;
		this.value = value;
	}

	@Override
	public void run() {
		b.addOccupant(p, pt);
		if(!p.getResourceSet().getPermanentEffects().isNoCardAreaEffect())b.immediateEffect(p, value);
		
		Building card = b.getBuilding();
		b.setBuilding(null);
		p.getResourceSet().getBuildingList().add(card);
		card.immediateEffect(p);
	}

}
