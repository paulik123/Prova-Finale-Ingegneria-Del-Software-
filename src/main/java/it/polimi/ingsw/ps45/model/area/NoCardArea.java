package it.polimi.ingsw.ps45.model.area;

import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.player.Player;

public class NoCardArea extends Area{
	Effect immediateEffect;

	public NoCardArea(int cost, int maxOccupants, Effect effect, String name){
		super();
		this.setCost(cost);
		this.maxOccupants = maxOccupants;
		this.immediateEffect = effect;
		super.setName(name);
	}

	@Override
	public void immediateEffect(Player p, int value) {
		immediateEffect.runEffect(p, value);
	}

}
