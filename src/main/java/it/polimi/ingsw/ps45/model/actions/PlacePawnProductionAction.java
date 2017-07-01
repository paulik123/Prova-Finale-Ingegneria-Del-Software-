package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.area.NoCardArea;
import it.polimi.ingsw.ps45.model.player.PawnType;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * The actual action that is executed by the player when he wants to do a PlacePawnProductionAction.
 */
public class PlacePawnProductionAction implements Action{
	private NoCardArea productionArea;
	private Player p;
	private PawnType pt;
	private int value;
	
	/**
 	 * Constructor
	 * @param  p the player which executes the action.
	 * @param  area the NoCardArea used in the action.
	 * @param pt the PawnType that the player will use
	 * @param value the value of the pawn + the value of the servants that have been added to the action
	 */
	protected PlacePawnProductionAction(Player p, NoCardArea area, PawnType pt, int value){
		this.productionArea = area;
		this.p = p;
		this.pt = pt;
		this.value = value;
	}
	
	/**
 	 * The method that runs the action.
	 */
	@Override
	public void run() {
		p.getResourceSet().setPawn(pt, 0, false);
		
		productionArea.addOccupant(p, pt);
		productionArea.immediateEffect(p, value);
	}
}
