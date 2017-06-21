package it.polimi.ingsw.ps45.model.effects;

import it.polimi.ingsw.ps45.model.player.Player;

public class CanPlacePawnOnOccupiedAreaEffect implements Effect{

	@Override
	public void runEffect(Player p, int value) {
		p.getResourceSet().getPermanentEffects().setCanPlacePawnOnOccupiedAreas(true);
	}

}
