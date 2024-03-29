package it.polimi.ingsw.ps45.model.actions.state;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;

/**
 * ActionBuilderState implementation that allows the player to make a TakeCharacterAction
 * @see the actual interface for documentation of each method
 */
public class TakeCharacterState extends ConcreteActionBuilderState{
	
	private String[] commands = {"nopawncharacter", "activateleader", "useleader","discardleader", "endturn"};
	private int value;
	private ConsumableSet discount;
	
	public TakeCharacterState(int value, ConsumableSet discount){
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
	public boolean takeCharacterAction() {
		// TODO Auto-generated method stub
		return true;
	}

	
	@Override
	public String message() {
		// TODO Auto-generated method stub
		return "TakeCharacterState";
	}

	@Override
	public String[] commands() {
		return commands;
	}
}
