package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.area.cardarea.BuildingCardArea;
import it.polimi.ingsw.ps45.model.cards.Building;
import it.polimi.ingsw.ps45.model.player.PawnType;
import it.polimi.ingsw.ps45.model.player.Player;


/**
 * The actual action that is executed by the player when he wants to do a PlacePawnBuildingAction.
 */
public class PlacePawnBuildingAction implements Action{
	
	private Player p;
	private BuildingCardArea b;
	private PawnType pt;
	private int value;
	
	/**
 	 * Constructor
	 * @param  p the player which executes the action.
	 * @param  b the BuildingCardArea used in the action.
	 * @param pt the PawnType that the player will use
	 * @param value the value of the pawn + the value of the servants that have been added to the action
	 */
	protected PlacePawnBuildingAction(Player p, BuildingCardArea b, PawnType pt, int value){
		this.p = p;
		this.b = b;
		this.pt = pt;
		this.value = value;
	}

	/**
 	 * The method that runs the action.
	 */
	@Override
	public void run() {
		b.addOccupant(p, pt);
		if(!p.getResourceSet().getPermanentEffects().isNoCardAreaEffect())b.immediateEffect(p, value);
		
		Building card = b.getBuilding();
		b.setBuilding(null);
		p.getResourceSet().getBuildingList().add(card);
		card.immediateEffect(p);
	}

}
