package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.area.cardarea.CharacterCardArea;
import it.polimi.ingsw.ps45.model.cards.Character;
import it.polimi.ingsw.ps45.model.player.PawnType;
import it.polimi.ingsw.ps45.model.player.Player;

public class PlacePawnCharacterAction implements Action{
	
	private Player p;
	private CharacterCardArea c;
	private PawnType pt;
	private int value;
	
	protected PlacePawnCharacterAction(Player p, CharacterCardArea c, PawnType pt, int value){
		this.p = p;
		this.c= c;
		this.pt = pt;
		this.value = value;
	}

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
