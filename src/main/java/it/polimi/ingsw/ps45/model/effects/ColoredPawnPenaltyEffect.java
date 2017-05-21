package it.polimi.ingsw.ps45.model.effects;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.player.PawnType;
import it.polimi.ingsw.ps45.model.player.Player;

public class ColoredPawnPenaltyEffect implements Effect, Serializable{

	int penalty;
	
	public ColoredPawnPenaltyEffect(int penalty){
		this.penalty = penalty;
	}
	
	@Override
	public void runEffect(Player p, int value) {
		p.getResourceSet().setModifierPawn(PawnType.BLACK, penalty + p.getResourceSet().getPawnModifier(PawnType.BLACK));
		p.getResourceSet().setModifierPawn(PawnType.WHITE, penalty + p.getResourceSet().getPawnModifier(PawnType.WHITE));
		p.getResourceSet().setModifierPawn(PawnType.ORANGE, penalty + p.getResourceSet().getPawnModifier(PawnType.ORANGE));
	}

}
