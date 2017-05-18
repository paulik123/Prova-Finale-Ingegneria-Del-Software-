package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.area.cardarea.BuildingCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.CharacterCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.VentureCardArea;
import it.polimi.ingsw.ps45.model.cards.Building;
import it.polimi.ingsw.ps45.model.cards.Venture;
import it.polimi.ingsw.ps45.model.player.PawnType;
import it.polimi.ingsw.ps45.model.player.Player;

public class PlacePawnVentureAction implements Action{
	
	private Player p;
	private VentureCardArea v;
	private PawnType pt;
	
	protected PlacePawnVentureAction(Player p, VentureCardArea v, PawnType pt){
		this.p = p;
		this.v = v;
		this.pt = pt;
	}

	@Override
	public void run() {
		p.getResourceSet().setPawn(pt, 0, false);
		
		v.addOccupant(p, pt);
		v.immediateEffect(p);
		
		Venture card = v.getVenture();
		v.setVenture(null);
		p.getResourceSet().getVentureList().add(card);
		card.immediateEffect(p);
	}

}
