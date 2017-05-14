package it.polimi.ingsw.ps45.model.area;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.player.Player;

public class HarvestAreaSmall extends Area {
	
	public HarvestAreaSmall(int cost){
		occupiedBy = new ArrayList<Player>();
		maxOccupants = 1;
		
		this.setCost(cost);
	}

	public void immediateEffect(Player p, int pawnValue) {
		p.harvest(pawnValue);
	}
	

}