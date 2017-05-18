package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.area.cardarea.CharacterCardArea;
import it.polimi.ingsw.ps45.model.cards.Character;
import it.polimi.ingsw.ps45.model.player.PawnType;
import it.polimi.ingsw.ps45.model.player.Player;

public class NoPawnCharacterAction implements Action{
	
	private Player p;
	private CharacterCardArea c;
	private PawnType pt;
	
	protected NoPawnCharacterAction(Player p, CharacterCardArea c){
		this.p = p;
		this.c = c;
	}

	@Override
	public void run() {
		c.immediateEffect(p);
		
		Character card = c.getCharacter();
		c.setCharacter(null);
		p.getResourceSet().getCharacterList().add(card);
		card.immediateEffect(p);
	}

}
