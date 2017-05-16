package it.polimi.ingsw.ps45.model.area;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.player.Pawn;
import it.polimi.ingsw.ps45.model.player.Player;

public class ProductionAreaSmall extends Area {

	private static int COST = 1;
	private static int OCCUPANTS = 999;
	
	public ProductionAreaSmall(){
		maxOccupants = OCCUPANTS;
		
		this.setCost(COST);
	}

	@Override
	public void immediateEffect(Player p) {
		//TODO: ab.setProductionAction
	}




	
}
