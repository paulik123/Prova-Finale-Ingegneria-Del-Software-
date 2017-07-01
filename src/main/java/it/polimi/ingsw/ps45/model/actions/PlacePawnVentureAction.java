package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.area.cardarea.VentureCardArea;
import it.polimi.ingsw.ps45.model.cards.Venture;
import it.polimi.ingsw.ps45.model.player.PawnType;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * The actual action that is executed by the player when he wants to do a PlacePawnVentureAction.
 */
public class PlacePawnVentureAction implements Action{
	
	private Player p;
	private VentureCardArea v;
	private PawnType pt;
	private int value;
	
	/**
 	 * Constructor
	 * @param p the player which executes the action.
	 * @param v the VentureCardArea used in the action.
	 * @param pt the PawnType that the player will use
	 * @param value the value of the pawn + the value of the servants that have been added to the action
	 */
	protected PlacePawnVentureAction(Player p, VentureCardArea v, PawnType pt, int value){
		this.p = p;
		this.v = v;
		this.pt = pt;
		this.value = value;
	}

	/**
 	 * The method that runs the action.
	 */
	@Override
	public void run() {
		
		v.addOccupant(p, pt);
		if(!p.getResourceSet().getPermanentEffects().isNoCardAreaEffect())v.immediateEffect(p, value);
		
		Venture card = v.getVenture();
		v.setVenture(null);
		p.getResourceSet().getVentureList().add(card);
		card.immediateEffect(p);
	}

}
