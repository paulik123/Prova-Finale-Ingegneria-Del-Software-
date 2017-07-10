package it.polimi.ingsw.ps45.model.actions.state;

/**
 * ActionBuilderState implementation that allows the player to make a CouncilPrivilegeOneAction
 * @see the actual interface for documentation of each method
 */
public class CouncilPrivilegeOneState extends ConcreteActionBuilderState{
	
	private String[] commands = {"cp1", "endturn"};

	@Override
	public boolean coucilPrivilegeActionOne() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String message() {
		// TODO Auto-generated method stub
		return "CouncilPrivilegeOneState";
	}

	@Override
	public String[] commands() {
		// TODO Auto-generated method stub
		return commands;
	}
	
}
