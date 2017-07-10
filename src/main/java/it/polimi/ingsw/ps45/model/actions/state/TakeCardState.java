package it.polimi.ingsw.ps45.model.actions.state;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;

/**
 * ActionBuilderState implementation that allows the player to make a TakeAnyCardAction
 * @see the actual interface for documentation of each method
 */
public class TakeCardState extends ConcreteActionBuilderState{
	
	private String[] commands = {"nopawnterritory", "nopawncharacter", "nopawnbuilding", "nopawnventure", "activateleader", "useleader", "endturn"};
	
	private int value;
	private ConsumableSet discount;
	
	public TakeCardState(int value, ConsumableSet discount){
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
	public boolean takeTerritoryAction() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean takeCharacterAction() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean takeBuildingAction() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean takeVentureAction() {
		// TODO Auto-generated method stub
		return true;
	}

	
	@Override
	public String message() {
		// TODO Auto-generated method stub
		return "TakeCardStateState";
	}

	@Override
	public String[] commands() {
		return commands;
	}

}
