package it.polimi.ingsw.ps45.model.actions.state;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;

/**
 * ActionBuilderState implementation that allows the player to make a TakeBuldingAction
 * @see the actual interface for documentation of each method
 */
public class TakeBuildingState extends ConcreteActionBuilderState{
	
	private String[] commands = {"nopawnbuilding", "activateleader", "useleader", "endturn"};
	
	private int value;
	private ConsumableSet discount;
	
	public TakeBuildingState(int value, ConsumableSet discount){
		this.value = value;
		this.discount = discount;
	}


	@Override
	public int actionValue() {
		// TODO Auto-generated method stub
		return value;
	}
	

	@Override
	public ConsumableSet discount() {
		// TODO Auto-generated method stub
		return discount;
	}

	@Override
	public boolean takeBuildingAction() {
		// TODO Auto-generated method stub
		return true;
	}

	
	@Override
	public String message() {
		// TODO Auto-generated method stub
		return "TakeBuildingState";
	}

	@Override
	public String[] commands() {
		return commands;
	}

}
