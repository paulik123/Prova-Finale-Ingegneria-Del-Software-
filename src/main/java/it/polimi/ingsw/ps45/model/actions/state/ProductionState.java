package it.polimi.ingsw.ps45.model.actions.state;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.vatican.ExcommunicationCard;

/**
 * ActionBuilderState implementation that allows the player to make a ProductionAction
 * @see the actual interface for documentation of each method
 */
public class ProductionState extends ConcreteActionBuilderState{
	
	private String[] commands = {"production", "activateleader", "useleader", "endturn"};
	
	private int value;
	public ProductionState(int value){
		this.value = value;
	}

	@Override
	public int actionValue() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public boolean productionAction() {
		// TODO Auto-generated method stub
		return true;
	}

	
	@Override
	public String message() {
		// TODO Auto-generated method stub
		return "ProductionActionState";
	}

	@Override
	public String[] commands() {
		return commands;
	}

}
