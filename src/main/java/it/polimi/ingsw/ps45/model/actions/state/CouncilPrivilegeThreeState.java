package it.polimi.ingsw.ps45.model.actions.state;

/**
 * ActionBuilderState implementation that allows the player to make a CouncilPrivilegeThreeAction
 * @see the actual interface for documentation of each method
 */
public class CouncilPrivilegeThreeState extends ConcreteActionBuilderState{
	
	private String[] commands = {"cp3", "endturn"};


	@Override
	public boolean coucilPrivilegeActionThree() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String message() {
		// TODO Auto-generated method stub
		return "CouncilPrivilegeTwoState";
	}

	@Override
	public String[] commands() {
		return commands;
	}
	
}
