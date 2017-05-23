package it.polimi.ingsw.ps45.model.cards;

import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

public class Character extends Card {
	
	ConsumableSet cost;
	
	public Character(Era e, String name, ConsumableSet cost) {
		super(e, name);
		// TODO Auto-generated constructor stub
	}

	public ConsumableSet cost(){
		return cost;
	}

	@Override
	public void immediateEffect(Player p) {
		for(Effect e:super.getEffects()){
			e.runEffect(p, 0);
		}
	}
}
