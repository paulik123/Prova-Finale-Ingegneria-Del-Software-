package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.area.cardarea.BuildingCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.CharacterCardArea;
import it.polimi.ingsw.ps45.model.cards.Building;
import it.polimi.ingsw.ps45.model.player.PawnType;
import it.polimi.ingsw.ps45.model.player.Player;

public class NoPawnBuildingAction implements Action{
	
	private Player p;
	private BuildingCardArea b;
	
	protected NoPawnBuildingAction(Player p, BuildingCardArea b, PawnType pt){
		this.p = p;
		this.b = b;
	}

	@Override
	public void run() {
		b.immediateEffect(p);
		
		Building card = b.getBuilding();
		b.setBuilding(null);
		p.getResourceSet().getBuildingList().add(card);
		card.immediateEffect(p);
	}

}
