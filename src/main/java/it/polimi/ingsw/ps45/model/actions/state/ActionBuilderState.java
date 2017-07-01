package it.polimi.ingsw.ps45.model.actions.state;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.vatican.ExcommunicationCard;

/**
 * Interface for the states that are used in the ActionBuilder
 */
public interface ActionBuilderState {
	/**
	 * @return      The value of the actions that can be executed in this state. Returns 0 if not needed.
	 */
	public int actionValue();
	
	/**
	 * @return The discount of the actions as a ConsumableSet. Can return null if not needed or doesn't exist.
	 */
	public ConsumableSet discount();
	
	/**
	 * @return An excommunication card used for certain actions. Can return null if not needed.
	 */
	public ExcommunicationCard getExcommunicationCard();
	
	/**
	 * @return a string "message" telling what actions he can do.
	 */
	public String message();
	
	/**
	 * @return A string array with all the commands he can do as strings.
	 */
	public String[] commands();
	
	
	
	/**
	 * @return true if the player can execute a PlacePawn action.
	 */
	public boolean placePawnAction();
	
	/**
	 * @return true if the player can execute a harvest action.
	 */
	public boolean productionAction();
	
	/**
	 * @return true if the player can execute a harvest action.
	 */
	public boolean harvestAction();
	
	/**
	 * @return true if the player can execute an addServantsToHarvestAction action.
	 */
	public boolean addServantsToHarvestAction();
	
	/**
	 * @return true if the player can execute an addServantsToProductionAction action.
	 */
	public boolean addServantsToProductionAction();
	
	/**
	 * @return true if the player can execute an takeTerritoryAction action.
	 */
	public boolean takeTerritoryAction();
	
	/**
	 * @return true if the player can execute an takeCharacterAction action.
	 */
	public boolean takeCharacterAction();
	
	/**
	 * @return true if the player can execute an takeBuildingAction action.
	 */
	public boolean takeBuildingAction();
	
	/**
	 * @return true if the player can execute an takeVentureAction action.
	 */
	public boolean takeVentureAction();
	
	/**
	 * @return true if the player can execute an coucilPrivilegeActionOne action.
	 */
	public boolean coucilPrivilegeActionOne();
	
	
	/**
	 * @return true if the player can execute an coucilPrivilegeActionTwo action.
	 */
	public boolean coucilPrivilegeActionTwo();
	
	/**
	 * @return true if the player can execute an coucilPrivilegeActionThree action.
	 */
	public boolean coucilPrivilegeActionThree();
	
	/**
	 * @return true if the player can execute an vaticanChoice action.
	 */
	public boolean vaticanChoice();
}
