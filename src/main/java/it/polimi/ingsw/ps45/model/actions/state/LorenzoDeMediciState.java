package it.polimi.ingsw.ps45.model.actions.state;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.vatican.ExcommunicationCard;

/**
 * ActionBuilderState implementation that allows the player to make a LorenzoDeMediciAction
 * @see the actual interface for documentation of each method
 */
public class LorenzoDeMediciState extends ConcreteActionBuilderState{
	
	private String[] commands = {"copyleadercard"};

	@Override
	public String[] commands() {
		// TODO Auto-generated method stub
		return commands;
	}

}
