package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.area.cardarea.CharacterCardArea;
import it.polimi.ingsw.ps45.model.cards.Character;
import it.polimi.ingsw.ps45.model.player.PawnType;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * The actual action that is executed by the player when he wants to do a PlacePawnCharacterAction.
 */
public class PlacePawnCharacterAction implements Action{
	
	private Player p;
	private CharacterCardArea c;
	private PawnType pt;
	private int value;
	
	/**
 	 * Constructor
	 * @param  p the player which executes the action.
	 * @param  c the CharacterCardArea used in the action.
	 * @param pt the PawnType that the player will use
	 * @param value the value of the pawn + the value of the servants that have been added to the action
	 */
	protected PlacePawnCharacterAction(Player p, CharacterCardArea c, PawnType pt, int value){
		this.p = p;
		this.c= c;
		this.pt = pt;
		this.value = value;
	}

	/**
 	 * The method that runs the action.
	 */
	@Override
	public void run() {
		
		c.addOccupant(p, pt);
		if(!p.getResourceSet().getPermanentEffects().isNoCardAreaEffect())c.immediateEffect(p, value);
		
		Character card = c.getCharacter();
		c.setCharacter(null);
		p.getResourceSet().getCharacterList().add(card);
		card.immediateEffect(p);
	}

}
