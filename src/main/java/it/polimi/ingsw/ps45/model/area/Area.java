package it.polimi.ingsw.ps45.model.area;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.player.PawnType;
import it.polimi.ingsw.ps45.model.player.Player;


/**
 * Abstract class that all area instances inherit from.
 */
public abstract class Area implements Serializable{
	private String name;
	private int cost;
	private ArrayList<PlayerPawnPair> occupiedBy;
	protected int maxOccupants;
	abstract public void immediateEffect(Player p, int value);
	
	
	/**
 	 * Constructor
 	 * Initializes the list of occupants.
	 */
	public Area(){
		occupiedBy = new ArrayList<PlayerPawnPair>();
	}
	
	/**
	 * @return The maximum number of pawns that can stay on this area.
	 */
	public int getMaxOccupants() {
		return maxOccupants;
	}


	/**
	 * maxOccupants setter.
	 * @param maxOccupants The maximum number of occupants.
	 */
	public void setMaxOccupants(int maxOccupants) {
		this.maxOccupants = maxOccupants;
	}
	
	/**
	 * @return The list of occupants of this area.
	 */
	public ArrayList<PlayerPawnPair> getOccupants(){
		return occupiedBy;
	}
	
	/**
	 * @return true if the area has is occupied by a colored pawn.
	 */
	public boolean isOccupiedByPlayerWithColoredPawn(Player p){
		for(PlayerPawnPair ppp:occupiedBy){
			if(ppp.getPlayer() == p && ppp.getType() != PawnType.NEUTRAL)  return true;
		}
		return false;
	}


	/**
	 * Adds an occupant to the occupants list
	 * @param p The player of the new occupant to be added. 
	 * @param type The pawn type that the players occupies the area with.
	 */
	public void addOccupant(Player p, PawnType type){
		if(isAvailable())occupiedBy.add(new PlayerPawnPair(p, type));
	}
	
	/**
	 * @return the (pawn)cost of the area.
	 */
	public int getCost(){
		return cost;
	}
	
	/**
	 * Sets the cost of the area
	 * @param cost The (pawn)cost of the area to be set. 
	 */
	protected void setCost(int cost){
		this.cost = cost;
	}

	/**
	 * @return true if the number of occupants is smaller than the maximum number of occupants.
	 */
	public boolean isAvailable(){
		return occupiedBy.size() < maxOccupants;
	}


	/**
	 * @return the name of the area as a String.
	 */
	public String getName() {
		return name;
	}


	/**
	 * Sets the name of the area
	 * @param name The name of the area to be set. 
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
