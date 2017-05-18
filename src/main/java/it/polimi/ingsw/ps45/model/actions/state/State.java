package it.polimi.ingsw.ps45.model.actions.state;

public interface State {
	public int actionValue();
	// PawnAction
	public boolean placePawnAction();
	
	// No Pawn Actions
	public boolean productionAction();
	public boolean harvestAction();
	public boolean takeTerritoryAction();
	public boolean takeCharacterAction();
	public boolean takeBuildingAction();
	public boolean takeVentureAction();
	public boolean coucilPrivilegeActionOne();
	public boolean coucilPrivilegeActionTwo();
	public boolean coucilPrivilegeActionThree();
}
