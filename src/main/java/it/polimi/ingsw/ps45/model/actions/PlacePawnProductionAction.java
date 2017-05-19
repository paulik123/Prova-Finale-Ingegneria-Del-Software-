package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.area.NoCardArea;
import it.polimi.ingsw.ps45.model.area.ProductionArea;
import it.polimi.ingsw.ps45.model.player.PawnType;
import it.polimi.ingsw.ps45.model.player.Player;

public class PlacePawnProductionAction implements Action{
	private ProductionArea productionArea;
	private Player p;
	private PawnType pt;
	private int value;
	
	protected PlacePawnProductionAction(Player p, ProductionArea area, PawnType pt, int value){
		this.productionArea = area;
		this.p = p;
		this.pt = pt;
		this.value = value;
	}
	
	@Override
	public void run() {
		p.getResourceSet().setPawn(pt, 0, false);
		
		productionArea.addOccupant(p, pt);
		productionArea.immediateEffect(p, value);
	}
}
