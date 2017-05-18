package it.polimi.ingsw.ps45.model.actions;

import it.polimi.ingsw.ps45.model.area.cardarea.CharacterCardArea;
import it.polimi.ingsw.ps45.model.cards.Character;
import it.polimi.ingsw.ps45.model.player.PawnType;
import it.polimi.ingsw.ps45.model.player.Player;

public class PlacePawnCharacterAction implements Action{
	
	private Player p;
	private CharacterCardArea c;
	private PawnType pt;
	
	protected PlacePawnCharacterAction(Player p, CharacterCardArea c, PawnType pt){
		this.p = p;
		this.c= c;
		this.pt = pt;
	}

	@Override
	public void run() {
		p.getResourceSet().setPawn(pt, 0, false);
		
		c.addOccupant(p, pt);
		c.immediateEffect(p);
		
		Character card = c.getCharacter();
		c.setCharacter(null);
		p.getResourceSet().getCharacterList().add(card);
		card.immediateEffect(p);
	}

}
