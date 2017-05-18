package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.area.cardarea.TerritoryCardArea;
import it.polimi.ingsw.ps45.model.cards.Territory;
import it.polimi.ingsw.ps45.model.player.PawnType;
import it.polimi.ingsw.ps45.model.player.Player;

public class PlacePawnTerritoryAction implements Action{
	
	private Player p;
	private TerritoryCardArea t;
	private PawnType pt;
	
	protected PlacePawnTerritoryAction(Player p, TerritoryCardArea t, PawnType pt){
		this.p = p;
		this.t= t;
		this.pt = pt;
	}

	@Override
	public void run() {
		p.getResourceSet().setPawn(pt, 0, false);
		
		t.addOccupant(p, pt);
		t.immediateEffect(p);
		
		Territory card = t.getTerritory();
		t.setTerritory(null);
		p.getResourceSet().getTerritoryList().add(card);
		card.immediateEffect(p);
	}

}
