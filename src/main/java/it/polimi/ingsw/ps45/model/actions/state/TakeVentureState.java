package it.polimi.ingsw.ps45.model.actions.state;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.vatican.ExcommunicationCard;

public  class TakeVentureState implements ActionBuilderState{
	
	private int value;
	private ConsumableSet discount;
	private String[] commands = {"nopawnventure"};
	
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
		return true;
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
	public ExcommunicationCard getExcommunicationCard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean vaticanChoice() {
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
	public String message() {
		// TODO Auto-generated method stub
		return "TakeVentureState";
	}

	@Override
	public String[] commands() {
		return commands;
	}

}
