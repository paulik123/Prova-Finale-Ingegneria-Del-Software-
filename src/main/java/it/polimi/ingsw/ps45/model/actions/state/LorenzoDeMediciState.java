package it.polimi.ingsw.ps45.model.actions.state;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.vatican.ExcommunicationCard;

/**
 * ActionBuilderState implementation that allows the player to make a LorenzoDeMediciAction
 * @see the actual interface for documentation of each method
 */
public class LorenzoDeMediciState implements ActionBuilderState{
	
	private String[] commands = {"copyleadercard"};

	@Override
	public int actionValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ConsumableSet discount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExcommunicationCard getExcommunicationCard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String message() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] commands() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean placePawnAction() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean productionAction() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean harvestAction() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addServantsToHarvestAction() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addServantsToProductionAction() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean takeTerritoryAction() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean takeCharacterAction() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean takeBuildingAction() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean takeVentureAction() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean coucilPrivilegeActionOne() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean coucilPrivilegeActionTwo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean coucilPrivilegeActionThree() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean vaticanChoice() {
		// TODO Auto-generated method stub
		return false;
	}

}
