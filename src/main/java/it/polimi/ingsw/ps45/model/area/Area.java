package it.polimi.ingsw.ps45.model.area;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.player.Player;

public abstract class Area {
	private int cost;
	protected ArrayList<Player> occupiedBy;
	protected int maxOccupants;
	public void addOccupant(Player p){
		if(isAvailable())occupiedBy.add(p);
	}
	public int getCost(){
		return cost;
	}
	protected void setCost(int cost){
		this.cost = cost;
	}

	public boolean isAvailable(){
		return occupiedBy.size() < maxOccupants;
	}
}
