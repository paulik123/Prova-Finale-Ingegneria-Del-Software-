package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.area.*;
import it.polimi.ingsw.ps45.model.player.PawnType;
import it.polimi.ingsw.ps45.model.player.Player;

public class PlacePawnAction implements Action{
	private NoCardArea noCardArea;
	private Player p;
	private PawnType pt;
	
	protected PlacePawnAction(Player p, NoCardArea area, PawnType pt){
		this.noCardArea = area;
		this.p = p;
		this.pt = pt;
	}
	
	@Override
	public void run() {
		p.getResourceSet().setPawn(pt, 0, false);
		
		noCardArea.addOccupant(p, pt);
		noCardArea.immediateEffect(p);
	}
}
