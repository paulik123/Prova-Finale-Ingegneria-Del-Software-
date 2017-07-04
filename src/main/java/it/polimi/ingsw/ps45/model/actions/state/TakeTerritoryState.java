package it.polimi.ingsw.ps45.model.actions.state;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.vatican.ExcommunicationCard;

/**
 * ActionBuilderState implementation that allows the player to make a TakeTerritoryAction
 * @see the actual interface for documentation of each method
 */
public class TakeTerritoryState extends ConcreteActionBuilderState{
	
	private int value;
	private ConsumableSet discount;
	private String[] commands = {"nopawnterritory", "activateleader", "useleader", "endturn"};
	
	public TakeTerritoryState(int value, ConsumableSet discount){
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
	public String message() {
		// TODO Auto-generated method stub
		return "TakeTerritoryState";
	}

	@Override
	public String[] commands() {
		return commands;
	}

}
