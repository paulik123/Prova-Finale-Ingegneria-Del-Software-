package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.area.cardarea.TerritoryCardArea;
import it.polimi.ingsw.ps45.model.cards.Territory;
import it.polimi.ingsw.ps45.model.player.PawnType;
import it.polimi.ingsw.ps45.model.player.Player;

public class NoPawnTerritoryAction implements Action{
	
	private Player p;
	private TerritoryCardArea t;
	
	protected NoPawnTerritoryAction(Player p, TerritoryCardArea t){
		this.p = p;
		this.t= t;
	}

	@Override
	public void run() {
		t.immediateEffect(p);
		
		Territory card = t.getTerritory();
		t.setTerritory(null);
		p.getResourceSet().getTerritoryList().add(card);
		card.immediateEffect(p);
	}

}
