package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.area.cardarea.TerritoryCardArea;
import it.polimi.ingsw.ps45.model.cards.Territory;
import it.polimi.ingsw.ps45.model.player.Player;

public class NoPawnTerritoryAction implements Action{
	
	private Player p;
	private TerritoryCardArea t;
	private int value;
	
	protected NoPawnTerritoryAction(Player p, TerritoryCardArea t){
		this.p = p;
		this.t= t;
		value = 0;
	}

	@Override
	public void run() {
		if(!p.getResourceSet().getPermanentEffects().isNoCardAreaEffect())t.immediateEffect(p, value);
		
		Territory card = t.getTerritory();
		t.setTerritory(null);
		p.getResourceSet().getTerritoryList().add(card);
		card.immediateEffect(p);
	}

}
