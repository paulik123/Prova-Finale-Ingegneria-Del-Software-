package it.polimi.ingsw.ps45.model.actions;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.actions.state.NoActionState;
import it.polimi.ingsw.ps45.model.cards.Territory;
import it.polimi.ingsw.ps45.model.player.PersonalBonusTile;
import it.polimi.ingsw.ps45.model.player.Player;

public class HarvestAction implements Action{
	
	private Player p;
	private int level;
	
	protected HarvestAction(Player p, int level){
		this.p = p;
		this.level = level;
	}

	@Override
	public void run() {
		ArrayList<Territory> territories = p.getResourceSet().getTerritoryList();
		
		PersonalBonusTile bt = p.getResourceSet().getBonusTile();
		if(level >=bt.getHarvestLevel())bt.harvest(p);
		
		for(Territory t:territories){
			if(level >= t.getHarvestLevel()) t.harvest(p);
		}	
	}

}
