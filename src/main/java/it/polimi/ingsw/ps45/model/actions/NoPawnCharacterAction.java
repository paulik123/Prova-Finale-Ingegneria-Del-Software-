package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.area.cardarea.CharacterCardArea;
import it.polimi.ingsw.ps45.model.cards.Character;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * The actual action that is executed by the player when he wants to do a NoPawnCharacterAction.
 */
public class NoPawnCharacterAction implements Action{
	
	private Player p;
	private CharacterCardArea c;
	private int value;
	
	/**
 	 * Constructor
	 * @param  p the player which executes the action.
	 * @param  c the CharacterCardArea used in the action.
	 */
	protected NoPawnCharacterAction(Player p, CharacterCardArea c){
		this.p = p;
		this.c = c;
		value = 0;
	}

	/**
 	 * The method that runs the action.
	 */
	@Override
	public void run() {	
		if(!p.getResourceSet().getPermanentEffects().isNoCardAreaEffect())c.immediateEffect(p, value);
		
		Character card = c.getCharacter();
		c.setCharacter(null);
		p.getResourceSet().getCharacterList().add(card);
		card.immediateEffect(p);
	}

}
