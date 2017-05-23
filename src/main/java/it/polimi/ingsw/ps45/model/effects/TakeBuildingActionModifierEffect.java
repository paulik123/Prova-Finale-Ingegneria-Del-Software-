package it.polimi.ingsw.ps45.model.effects;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.player.Player;

public class TakeBuildingActionModifierEffect implements Effect, Serializable{

	int effectValue;
	
	public TakeBuildingActionModifierEffect(int effectValue){
		this.effectValue = effectValue;
	}
	
	@Override
	public void runEffect(Player p, int value) {
		int h = p.getResourceSet().getActionValueModifier().getBuildingAction() + effectValue;
		p.getResourceSet().getActionValueModifier().setBuildingAction(h);
	}

}
