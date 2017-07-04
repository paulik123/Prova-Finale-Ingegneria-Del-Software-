package it.polimi.ingsw.ps45.model.actions.state;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.vatican.ExcommunicationCard;

/**
 * ActionBuilderState implementation that allows the player to make a AddServantsToProductionAction
 * @see the actual interface for documentation of each method
 */
public class AddServantsToProductionState extends ConcreteActionBuilderState{

	int value;
	private String[] commands = {"addservantsproduction", "activateleader", "useleader", "endturn"};
	
	public AddServantsToProductionState(int value){
		this.value = value;
	}

	@Override
	public int actionValue() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public boolean addServantsToProductionAction() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String message() {
		// TODO Auto-generated method stub
		return "AddServantsToProductionState";
	}

	@Override
	public String[] commands() {
		return commands;
	}

}
