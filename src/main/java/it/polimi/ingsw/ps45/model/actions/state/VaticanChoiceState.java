package it.polimi.ingsw.ps45.model.actions.state;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.vatican.ExcommunicationCard;

/**
 * ActionBuilderState implementation that allows the player to make a VaticanAction
 * @see the actual interface for documentation of each method
 */
public class VaticanChoiceState extends ConcreteActionBuilderState{
	
	private ExcommunicationCard c;
	private String[] commands = {"acceptvatican", "refusevatican"};
	
	public VaticanChoiceState(ExcommunicationCard c){
		this.c = c;
	}

	@Override
	public ExcommunicationCard getExcommunicationCard() {
		// TODO Auto-generated method stub
		return c;
	}

	@Override
	public boolean vaticanChoice() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public String message() {
		// TODO Auto-generated method stub
		return "VaticanChoiceState";
	}

	@Override
	public String[] commands() {
		return commands;
	}

}
