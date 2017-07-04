package it.polimi.ingsw.ps45.model.player;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps45.model.actions.ActionBuilder;
import it.polimi.ingsw.ps45.model.area.Board;
import it.polimi.ingsw.ps45.model.cards.Era;
import it.polimi.ingsw.ps45.model.cards.LeaderCard;
import it.polimi.ingsw.ps45.model.game.Observer;

/**
 * Player contains all the data the model needs to represent a player.
 */
public class Player {
	private String playerID;
	private String color;

	private  ResourceSet resourceSet;
	private transient ActionBuilder actionBuilder;
	
	public static final int defaultWood = 2;
	public static final int defaultStone = 2;
	public static final int defaultServants = 3;
	public static final int defaultCoins = 5;
	
	private boolean answeredVatican;
	
	private HashMap<Era, Boolean> vaticanPenalties;
	
	private String status;
	private String[] availableCommands;
	private boolean disconnected;
	
	
	/**
 	 * Constructor
	 * @param playerID the unique ID of the player
	 * @param bonusTile the id of the bonusTile the player has chosen it order for it to be initialized.
	 * @param color the color of the player. The game assigns this automatically based on the number of players already present.
	 * @param board the current board of the game the player is created in. Used in the player ActionBuilder to check conditions.
	 * @param initialResources The initial resources the player will have. Also decided by the game depending on the number of players.
	 * @param leaderCards a list of leader cards the player will own and can activate when he meets the requirements.
	 * @param errorObserver the observer that will be notified when the player makes an error.
	 */
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
	
	
	
	/**
	 * @return the player's action builder.
	 */
	public ActionBuilder getActionBuilder() {
		return actionBuilder;
	}
	
	/**
	 * Sets the player current board.
	 * @param  b the new board.
	 */
	public void updateBoard(Board b){
		actionBuilder.setBoard(b);
	}
	
	
	/**
	 * Initializes the HashMap that records if the player has penalties given by vatican according to Era.
	 */
	private void initializeVaticanPenalties(){
		vaticanPenalties = new HashMap<Era, Boolean>();
		vaticanPenalties.put(Era.I, false);
		vaticanPenalties.put(Era.II, false);
		vaticanPenalties.put(Era.III, false);
	}



	/**
	 * @return the player's resource set.
	 */
	public ResourceSet getResourceSet(){
		return resourceSet;
	}
	
	/**
	 * @return the player's unique id.
	 */
	public String getPlayerID() {
		return playerID;
	}
	
	
	/**
	 * Changes the player current observer with a new one.
	 * @param o the new observer.
	 */
	public void changeObserver(Observer o){
		actionBuilder.changeObserver(o);
	}



	/**
	 * Says that a player has answered vatican or not. Used in vatican turns.
	 * @return true if the player has answered vatican at the end of the last era.
	 */
	public boolean hasAnsweredVatican() {
		return answeredVatican;
	}
	
	/**
	 * @param e the era
	 * @return true if the player has suffered a penalty by vatican in the given era.
	 */
	public boolean hasVaticanPenalty(Era e){
		return vaticanPenalties.get(e);
	}
	
	/**
	 * Marks the player as punished by vatican at the end of the given era.
	 * @param e the era the player has been punished.
	 */
	public void setVaticanPenalty(Era e){
		vaticanPenalties.put(e, true);
	}



	/**
	 * Says if the player has answered vatican in the last era.
	 * @param answeredVatican true if he did, false if he didn't.
	 */
	public void setAnsweredVatican(boolean answeredVatican) {
		this.answeredVatican = answeredVatican;
	}




	/**
	 * @return the player's current status.
	 */
	public String getStatus() {
		return status;
	}




	/**
	 * Sets the player's status
	 * @param status the new status.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * @return the player's color.
	 */
	public String getColor(){
		return color;
	}
	
	/**
	 * Sets the player's available commands.
	 * @param status the new status.
	 */
	public void setAvailableCommands(String[] commands){
		this.availableCommands = commands;
	}
	
	/**
	 * @return an array of commands the player can currently make.
	 */
	public String[] getAvailableCommands(){
		return availableCommands;
	}



	/**
	 * @return true if the player was marked as disconnected by the game.
	 */
	public boolean isDisconnected() {
		return disconnected;
	}


	/**
	 * Sets the "disconnected" status of the player. If true his turn will be skipped.
	 * @param disconnected the new value of the "disconnected" status.
	 */
	public void setDisconnected(boolean disconnected) {
		this.disconnected = disconnected;
	}

	
}
