package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.area.cardarea.TerritoryCardArea;
import it.polimi.ingsw.ps45.model.cards.Territory;
import it.polimi.ingsw.ps45.model.player.PawnType;
import it.polimi.ingsw.ps45.model.player.Player;

public class PlacePawnTerritoryAction implements Action{
	
	private Player p;
	private TerritoryCardArea t;
	private PawnType pt;
	private int value;
	
	protected PlacePawnTerritoryAction(Player p, TerritoryCardArea t, PawnType pt, int value){
		this.p = p;
		this.t= t;
		this.pt = pt;
		this.value = value;
	}

	@Override
	public void run() {
		t.addOccupant(p, pt);
		t.immediateEffect(p, value);
		
		Territory card = t.getTerritory();
		t.setTerritory(null);
		p.getResourceSet().getTerritoryList().add(card);
		card.immediateEffect(p);
	}

}
