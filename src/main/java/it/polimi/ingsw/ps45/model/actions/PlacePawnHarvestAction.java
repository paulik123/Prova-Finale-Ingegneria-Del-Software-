package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.area.NoCardArea;
import it.polimi.ingsw.ps45.model.player.PawnType;
import it.polimi.ingsw.ps45.model.player.Player;

public class PlacePawnHarvestAction implements Action{
	private NoCardArea HarvestArea;
	private Player p;
	private PawnType pt;
	private int value;
	
	protected PlacePawnHarvestAction(Player p, NoCardArea area, PawnType pt, int value){
		this.HarvestArea = area;
		this.p = p;
		this.pt = pt;
		this.value = value;
	}
	
	@Override
	public void run() {
		HarvestArea.addOccupant(p, pt);
		HarvestArea.immediateEffect(p, value);
	}
}
