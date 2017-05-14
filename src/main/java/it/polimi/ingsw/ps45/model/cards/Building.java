package it.polimi.ingsw.ps45.model.cards;

import it.polimi.ingsw.ps45.model.player.Player;

public abstract class Building extends Card {
	private int level;
	private BuildingMode mode;
	public BuildingMode getMode() {
		return mode;
	}

	public void setMode(BuildingMode mode) {
		this.mode = mode;
	}

	abstract public void production(Player p);
	//TODO: IMPLEMENT SECOND MODE IN PRODUCTION
	
	public int getLevel(){
		return level;
	}
}
