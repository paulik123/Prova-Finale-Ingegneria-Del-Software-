package it.polimi.ingsw.ps45.model.actions.state;

/**
 * ActionBuilderState implementation that allows the player to make a CouncilPrivilegeTwoAction
 * @see the actual interface for documentation of each method
 */
public class CouncilPrivilegeTwoState extends ConcreteActionBuilderState{
	
	private String[] commands = {"cp2", "endturn"};


	@Override
	public boolean coucilPrivilegeActionTwo() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public String message() {
		// TODO Auto-generated method stub
		return "CouncilPrivilegeThreeState";
	}

	@Override
	public String[] commands() {
		return commands;
	}

}
