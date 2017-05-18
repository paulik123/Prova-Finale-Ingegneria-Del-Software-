package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.area.cardarea.BuildingCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.CharacterCardArea;
import it.polimi.ingsw.ps45.model.cards.Building;
import it.polimi.ingsw.ps45.model.player.PawnType;
import it.polimi.ingsw.ps45.model.player.Player;

public class PlacePawnBuildingAction implements Action{
	
	private Player p;
	private BuildingCardArea b;
	private PawnType pt;
	
	protected PlacePawnBuildingAction(Player p, BuildingCardArea b, PawnType pt){
		this.p = p;
		this.b = b;
		this.pt = pt;
	}

	@Override
	public void run() {
		p.getResourceSet().setPawn(pt, 0, false);
		
		b.addOccupant(p, pt);
		b.immediateEffect(p);
		
		Building card = b.getBuilding();
		b.setBuilding(null);
		p.getResourceSet().getBuildingList().add(card);
		card.immediateEffect(p);
	}

}
