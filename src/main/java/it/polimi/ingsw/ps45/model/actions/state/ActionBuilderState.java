package it.polimi.ingsw.ps45.model.actions.state;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.vatican.ExcommunicationCard;

public interface ActionBuilderState {
	public int actionValue();
	public ConsumableSet discount();
	public ExcommunicationCard getExcommunicationCard();
	public String message();
	// PawnAction
	public boolean placePawnAction();
	
	// No Pawn Actions
	public boolean productionAction();
	public boolean harvestAction();
	public boolean addServantsToHarvestAction();
	public boolean addServantsToProductionAction();
	public boolean takeTerritoryAction();
	public boolean takeCharacterAction();
	public boolean takeBuildingAction();
	public boolean takeVentureAction();
	public boolean coucilPrivilegeActionOne();
	public boolean coucilPrivilegeActionTwo();
	public boolean coucilPrivilegeActionThree();
	public boolean vaticanChoice();
}
