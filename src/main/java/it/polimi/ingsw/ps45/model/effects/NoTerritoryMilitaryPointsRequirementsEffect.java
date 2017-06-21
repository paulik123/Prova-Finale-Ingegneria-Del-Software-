package it.polimi.ingsw.ps45.model.effects;

import it.polimi.ingsw.ps45.model.player.Player;

public class NoTerritoryMilitaryPointsRequirementsEffect implements Effect{

	@Override
	public void runEffect(Player p, int value) {
		p.getResourceSet().getPermanentEffects().setNoTerritoryMilitaryPointsRequirements(true);
	}

}
