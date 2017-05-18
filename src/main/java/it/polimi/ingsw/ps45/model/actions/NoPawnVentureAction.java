package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.area.cardarea.BuildingCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.CharacterCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.VentureCardArea;
import it.polimi.ingsw.ps45.model.cards.Building;
import it.polimi.ingsw.ps45.model.cards.Venture;
import it.polimi.ingsw.ps45.model.player.PawnType;
import it.polimi.ingsw.ps45.model.player.Player;

public class NoPawnVentureAction implements Action{
	
	private Player p;
	private VentureCardArea v;

	
	protected NoPawnVentureAction(Player p, VentureCardArea v){
		this.p = p;
		this.v = v;
	}

	@Override
	public void run() {
		v.immediateEffect(p);
		
		Venture card = v.getVenture();
		v.setVenture(null);
		p.getResourceSet().getVentureList().add(card);
		card.immediateEffect(p);
	}

}
