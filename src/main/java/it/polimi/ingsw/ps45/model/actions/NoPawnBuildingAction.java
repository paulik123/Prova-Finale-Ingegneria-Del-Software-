package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.area.cardarea.BuildingCardArea;
import it.polimi.ingsw.ps45.model.cards.Building;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * The actual action that is executed by the player when he wants to do a NoPawnBuildingAction.
 */
public class NoPawnBuildingAction implements Action{
	
	private Player p;
	private BuildingCardArea b;
	private int value;
	
	/**
 	 * Constructor
	 * @param  p the player which executes the action.
	 * @param  b the BuildingCardArea used in the action.
	 */
	protected NoPawnBuildingAction(Player p, BuildingCardArea b){
		this.p = p;
		this.b = b;
		value = 0;
	}

	/**
 	 * The method that runs the action.
	 */
	@Override
	public void run() {	
		if(!p.getResourceSet().getPermanentEffects().isNoCardAreaEffect())b.immediateEffect(p,value);
		
		Building card = b.getBuilding();
		b.setBuilding(null);
		p.getResourceSet().getBuildingList().add(card);
		card.immediateEffect(p);
	}

}
