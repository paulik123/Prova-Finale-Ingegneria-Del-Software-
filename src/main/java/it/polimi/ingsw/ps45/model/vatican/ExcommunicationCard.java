package it.polimi.ingsw.ps45.model.vatican;

import it.polimi.ingsw.ps45.model.cards.Era;
import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.player.Player;

public class ExcommunicationCard {
	Era era;
	Effect effect;
	
	public void immediateEffect(Player p, int value){
		effect.runEffect(p, value);
	}
}
