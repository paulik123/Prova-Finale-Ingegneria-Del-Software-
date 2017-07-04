package it.polimi.ingsw.ps45.model.actions;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.cards.Territory;
import it.polimi.ingsw.ps45.model.player.PersonalBonusTile;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * The actual actions that is executed by the player when he wants to run a production.
 */
public class HarvestAction implements Action{
	
	private Player p;
	private int level;
	
	/**
 	 * Constructor
	 * @param  p the player to which the card leader's effects will be executed on.
	 * @param  level the level of the production.
	 */
	protected HarvestAction(Player p, int level){
		this.p = p;
		this.level = level;
	}

	/**
 	 * The method that runs the action.
	 */
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
