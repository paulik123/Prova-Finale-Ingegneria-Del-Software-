package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.area.cardarea.VentureCardArea;
import it.polimi.ingsw.ps45.model.cards.Venture;
import it.polimi.ingsw.ps45.model.player.Player;


/**
 * The actual action that is executed by the player when he wants to do a NoPawnVentureAction.
 */
public class NoPawnVentureAction implements Action{
	
	private Player p;
	private VentureCardArea v;
	private int value;

	/**
 	 * Constructor
	 * @param  p the player which executes the action.
	 * @param  v the VentureCardArea used in the action.
	 */
	protected NoPawnVentureAction(Player p, VentureCardArea v){
		this.p = p;
		this.v = v;
		value = 0;
	}

	/**
 	 * The method that runs the action.
	 */
	@Override
	public void run() {
		if(!p.getResourceSet().getPermanentEffects().isNoCardAreaEffect())v.immediateEffect(p,value);
		
		Venture card = v.getVenture();
		v.setVenture(null);
		p.getResourceSet().getVentureList().add(card);
		card.immediateEffect(p);
	}

}
