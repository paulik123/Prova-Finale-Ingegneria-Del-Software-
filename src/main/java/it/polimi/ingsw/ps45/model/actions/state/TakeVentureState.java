package it.polimi.ingsw.ps45.model.actions.state;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;

/**
 * ActionBuilderState implementation that allows the player to make a TakeVentureAction
 * @see the actual interface for documentation of each method
 */
public  class TakeVentureState extends ConcreteActionBuilderState{
	
	private int value;
	private ConsumableSet discount;
	private String[] commands = {"nopawnventure", "activateleader", "useleader", "endturn"};
	
	public TakeVentureState(int value, ConsumableSet discount){
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
	public boolean takeVentureAction() {
		// TODO Auto-generated method stub
		return true;
	}

	
	@Override
	public String message() {
		// TODO Auto-generated method stub
		return "TakeVentureState";
	}

	@Override
	public String[] commands() {
		return commands;
	}

}
