package it.polimi.ingsw.ps45.model.effects;

import it.polimi.ingsw.ps45.model.actions.state.LorenzoDeMediciState;
import it.polimi.ingsw.ps45.model.player.Player;

public class LorenzoDeMediciEffect implements Effect{

	@Override
	public void runEffect(Player p, int value) {
		p.getActionBuilder().setState(new LorenzoDeMediciState());
		
	}

}
