package it.polimi.ingsw.ps45.model.player;

import it.polimi.ingsw.ps45.model.actions.ActionBuilder;
import it.polimi.ingsw.ps45.model.area.Board;

public class Player {
	private String playerID;
	
	
	




	private ResourceSet resourceSet;
	private ActionBuilder actionBuilder;
	
	public static int defaultWood = 2;
	public static int defaultStone = 2;
	public static int defaultServants = 3;
	public static int defaultCoins = 5;
	
	private boolean answeredVatican;
	
	public Player(String playerID, Board board, ConsumableSet initialResources){
		resourceSet = new ResourceSet(initialResources);
		actionBuilder = new ActionBuilder(this, board);
		answeredVatican = false;
	}
	
	
	
	
	public ActionBuilder getActionBuilder() {
		return actionBuilder;
	}



	public ResourceSet getResourceSet(){
		return resourceSet;
	}
	
	public String getPlayerID() {
		return playerID;
	}




	public boolean hasAnsweredVatican() {
		return answeredVatican;
	}




	public void setAnsweredVatican(boolean answeredVatican) {
		this.answeredVatican = answeredVatican;
	}
	
	
	
	
}
