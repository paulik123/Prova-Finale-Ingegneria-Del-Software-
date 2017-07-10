package it.polimi.ingsw.ps45.model.actions.state;

/**
 * ActionBuilderState implementation that allows the player to make no action
 * @see the actual interface for documentation of each method
 */
public class NoActionState extends ConcreteActionBuilderState{
	
	private String[] commands = {"endturn"};
	
	@Override
	public String message() {
		// TODO Auto-generated method stub
		return "NoActionState";
	}

	@Override
	public String[] commands() {
		return commands;
	}

}
