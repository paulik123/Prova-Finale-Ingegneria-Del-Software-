package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.area.cardarea.VentureCardArea;
import it.polimi.ingsw.ps45.model.cards.Venture;
import it.polimi.ingsw.ps45.model.player.PawnType;
import it.polimi.ingsw.ps45.model.player.Player;

public class PlacePawnVentureAction implements Action{
	
	private Player p;
	private VentureCardArea v;
	private PawnType pt;
	private int value;
	
	protected PlacePawnVentureAction(Player p, VentureCardArea v, PawnType pt, int value){
		this.p = p;
		this.v = v;
		this.pt = pt;
		this.value = value;
	}

	@Override
	public void run() {
		
		v.addOccupant(p, pt);
		v.immediateEffect(p, value);
		
		Venture card = v.getVenture();
		v.setVenture(null);
		p.getResourceSet().getVentureList().add(card);
		card.immediateEffect(p);
	}

}
