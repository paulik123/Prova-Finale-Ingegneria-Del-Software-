package it.polimi.ingsw.ps45.model.area;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.player.PawnType;
import it.polimi.ingsw.ps45.model.player.Player;

public abstract class Area {
	private int cost;
	protected ArrayList<PlayerPawnPair> occupiedBy;
	protected int maxOccupants;
	public void addOccupant(Player p, PawnType type){
		if(isAvailable())occupiedBy.add(new PlayerPawnPair(p, type));
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
