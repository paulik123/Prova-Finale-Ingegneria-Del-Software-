package it.polimi.ingsw.ps45.model.area;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.actions.ActionBuilder;
import it.polimi.ingsw.ps45.model.player.PawnType;
import it.polimi.ingsw.ps45.model.player.Player;

public abstract class Area implements Serializable{
	private int cost;
	private ArrayList<PlayerPawnPair> occupiedBy;
	protected int maxOccupants;
	abstract public void immediateEffect(Player p, int value);
	
	
	public Area(){
		occupiedBy = new ArrayList<PlayerPawnPair>();
	}
	
	public int getMaxOccupants() {
		return maxOccupants;
	}


	public void setMaxOccupants(int maxOccupants) {
		this.maxOccupants = maxOccupants;
	}
	
	public ArrayList<PlayerPawnPair> getOccupants(){
		return occupiedBy;
	}
	
	public boolean isOccupiedByPlayerWithColoredPawn(Player p){
		for(PlayerPawnPair ppp:occupiedBy){
			if(ppp.getPlayer() == p && ppp.getType() != PawnType.NEUTRAL)  return true;
		}
		return false;
	}


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
