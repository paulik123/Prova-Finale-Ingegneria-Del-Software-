package it.polimi.ingsw.ps45.model.player;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps45.model.actions.ActionBuilder;
import it.polimi.ingsw.ps45.model.area.Board;
import it.polimi.ingsw.ps45.model.cards.Era;
import it.polimi.ingsw.ps45.model.cards.LeaderCard;
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
	
	private HashMap<Era, Boolean> vaticanPenalties;
	
	private String status;
	private String[] availableCommands;
	private boolean disconnected;
	
	
	public Player(String playerID, String bonusTile, String color, Board board, ConsumableSet initialResources, ArrayList<LeaderCard> leaderCards, Observer errorObserver){
		this.playerID = playerID;
		this.color = color;
		resourceSet = new ResourceSet(initialResources, bonusTile, leaderCards);
		actionBuilder = new ActionBuilder(this, board, errorObserver);
		answeredVatican = false;
		initializeVaticanPenalties();
		this.status = "New player";
		this.availableCommands = actionBuilder.getState().commands();
	}
	
	
	
	
	public ActionBuilder getActionBuilder() {
		return actionBuilder;
	}
	
	public void updateBoard(Board b){
		actionBuilder.setBoard(b);
	}
	
	private void initializeVaticanPenalties(){
		vaticanPenalties = new HashMap<Era, Boolean>();
		vaticanPenalties.put(Era.I, false);
		vaticanPenalties.put(Era.II, false);
		vaticanPenalties.put(Era.III, false);
	}



	public ResourceSet getResourceSet(){
		return resourceSet;
	}
	
	public String getPlayerID() {
		return playerID;
	}
	
	public void changeObserver(Observer o){
		actionBuilder.changeObserver(o);
	}




	public boolean hasAnsweredVatican() {
		return answeredVatican;
	}
	
	public boolean hasVaticanPenalty(Era e){
		return vaticanPenalties.get(e);
	}
	
	public void setVaticanPenalty(Era e){
		vaticanPenalties.put(e, true);
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




	public boolean isDisconnected() {
		return disconnected;
	}




	public void setDisconnected(boolean disconnected) {
		this.disconnected = disconnected;
	}
	
	
	

	



	
	
	
	
	
	
}
