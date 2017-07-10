package it.polimi.ingsw.ps45.model.actions.state;

/**
 * ActionBuilderState implementation that allows the player to make a PawnAction
 * @see the actual interface for documentation of each method
 */
public class PawnActionState extends ConcreteActionBuilderState{
	
	private String[] commands = {"placepawnnocard", "placepawnharvest", "placepawnproduction", "placepawnterritory", "placepawncharacter", "placepawnbuilding", "placepawnventure", "activateleader", "useleader", "endturn"};

	@Override
	public boolean placePawnAction() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String message() {
		// TODO Auto-generated method stub
		return "PawnActionState";
	}

	@Override
	public String[] commands() {
		return commands;
	}

}
