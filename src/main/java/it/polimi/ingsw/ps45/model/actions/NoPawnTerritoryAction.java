package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.area.cardarea.TerritoryCardArea;
import it.polimi.ingsw.ps45.model.cards.Territory;
import it.polimi.ingsw.ps45.model.player.Player;


/**
 * The actual action that is executed by the player when he wants to do a NoPawnTerritoryAction.
 */
public class NoPawnTerritoryAction implements Action{
	
	private Player p;
	private TerritoryCardArea t;
	private int value;
	
	/**
 	 * Constructor
	 * @param  p the player which executes the action.
	 * @param  t the TerritoryCardArea used in the action.
	 */
	protected NoPawnTerritoryAction(Player p, TerritoryCardArea t){
		this.p = p;
		this.t= t;
		value = 0;
	}

	/**
 	 * The method that runs the action.
	 */
	@Override
	public void run() {
		if(!p.getResourceSet().getPermanentEffects().isNoCardAreaEffect())t.immediateEffect(p, value);
		
		Territory card = t.getTerritory();
		t.setTerritory(null);
		p.getResourceSet().getTerritoryList().add(card);
		card.immediateEffect(p);
	}

}
