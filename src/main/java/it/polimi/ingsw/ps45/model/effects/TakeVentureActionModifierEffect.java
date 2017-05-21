package it.polimi.ingsw.ps45.model.effects;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.player.Player;

public class TakeVentureActionModifierEffect implements Effect, Serializable{

	int effectValue;
	
	public TakeVentureActionModifierEffect(int effectValue){
		this.effectValue = effectValue;
	}
	
	@Override
	public void runEffect(Player p, int value) {
		p.getResourceSet().getActionValueModifier().setVentureAction(effectValue);
	}

}
