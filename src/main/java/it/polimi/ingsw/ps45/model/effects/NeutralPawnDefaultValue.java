package it.polimi.ingsw.ps45.model.effects;

import it.polimi.ingsw.ps45.model.player.Player;

public class NeutralPawnDefaultValue implements Effect{
	
	int value;
	
	public NeutralPawnDefaultValue(int value){
		this.value = value;
	}

	@Override
	public void runEffect(Player p, int value) {
		int oldValue = p.getResourceSet().getPawnSet().getDefaultNeutralValue();
		p.getResourceSet().getPawnSet().setDefaultNeutralValue(oldValue + value);
	}

}
