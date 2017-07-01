package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.area.cardarea.TerritoryCardArea;
import it.polimi.ingsw.ps45.model.cards.Territory;
import it.polimi.ingsw.ps45.model.player.PawnType;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * The actual action that is executed by the player when he wants to do a PlacePawnTerritoryAction.
 */
public class PlacePawnTerritoryAction implements Action{
	
	private Player p;
	private TerritoryCardArea t;
	private PawnType pt;
	private int value;
	
	/**
 	 * Constructor
	 * @param p the player which executes the action.
	 * @param t the TerritoryCardArea used in the action.
	 * @param pt the PawnType that the player will use
	 * @param value the value of the pawn + the value of the servants that have been added to the action
	 */
	protected PlacePawnTerritoryAction(Player p, TerritoryCardArea t, PawnType pt, int value){
		this.p = p;
		this.t= t;
		this.pt = pt;
		this.value = value;
	}

	/**
 	 * The method that runs the action.
	 */
	@Override
	public void run() {
		t.addOccupant(p, pt);
		if(!p.getResourceSet().getPermanentEffects().isNoCardAreaEffect())t.immediateEffect(p, value);
		
		Territory card = t.getTerritory();
		t.setTerritory(null);
		p.getResourceSet().getTerritoryList().add(card);
		card.immediateEffect(p);
	}

}
