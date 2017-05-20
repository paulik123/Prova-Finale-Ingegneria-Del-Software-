package it.polimi.ingsw.ps45.model.player;

import it.polimi.ingsw.ps45.model.actions.ActionBuilder;
import it.polimi.ingsw.ps45.model.area.Board;

public class Player {
	private ResourceSet resourceSet;
	private ActionBuilder actionBuilder;
	
	public static int defaultWood = 2;
	public static int defaultStone = 2;
	public static int defaultServants = 3;
	public static int defaultCoins = 5;
	
	public Player(Board board, ConsumableSet initialResources){
		resourceSet = new ResourceSet(initialResources);
		actionBuilder = new ActionBuilder(this, board);
	}
	
	
	
	public ActionBuilder getActionBuilder() {
		return actionBuilder;
	}



	public ResourceSet getResourceSet(){
		return resourceSet;
	}
	
	
}
