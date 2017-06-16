package it.polimi.ingsw.ps45.model.player;

import it.polimi.ingsw.ps45.model.actions.ActionBuilder;
import it.polimi.ingsw.ps45.model.area.Board;
import it.polimi.ingsw.ps45.model.game.Observer;

public class Player {
	private String playerID;
	private String color;

	private  ResourceSet resourceSet;
	private transient ActionBuilder actionBuilder;
	
	public static int defaultWood = 2;
	public static int defaultStone = 2;
	public static int defaultServants = 3;
	public static int defaultCoins = 5;
	
	private boolean answeredVatican;
	
	private String status;
	private String[] availableCommands;
	
	
	public Player(String playerID, String color, Board board, ConsumableSet initialResources, Observer errorObserver){
		this.playerID = playerID;
		this.color = color;
		resourceSet = new ResourceSet(initialResources);
		actionBuilder = new ActionBuilder(this, board, errorObserver);
		answeredVatican = false;
		this.status = "New player";
		this.availableCommands = actionBuilder.getState().commands();
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




	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getColor(){
		return color;
	}
	
	public void setAvailableCommands(String[] commands){
		this.availableCommands = commands;
	}
	public String[] getAvailableCommands(){
		return availableCommands;
	}

	



	
	
	
	
	
	
}
