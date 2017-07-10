package it.polimi.ingsw.ps45.model.actions.state;

/**
 * ActionBuilderState implementation that allows the player to make a HarvestAction
 * @see the actual interface for documentation of each method
 */
public class HarvestState extends ConcreteActionBuilderState{
	
	private String[] commands = {"harvest", "endturn"};
	
	private int value;

	public HarvestState(int value){
		this.value = value;
	}

	@Override
	public int actionValue() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public boolean harvestAction() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String message() {
		// TODO Auto-generated method stub
		return "HarvestState";
	}

	@Override
	public String[] commands() {
		return commands;
	}


}
