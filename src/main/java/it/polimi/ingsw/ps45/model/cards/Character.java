package it.polimi.ingsw.ps45.model.cards;

import it.polimi.ingsw.ps45.model.player.ConsumableSet;

/**
 * Character Card - has a consumable cost. 
 */
public class Character extends Card {
	
	private ConsumableSet cost;
	
	/**
 	 * Constructor
 	 * @param e The era in which the card belongs to.
 	 * @param name The name of the card.
 	 * @param cost the cost of the card as a consumableset.
	 */
	public Character(Era e, String name, ConsumableSet cost) {
		super(e, name);
		this.cost = cost;
	}

	/**
	 * @return the cost of the card as a ConsumableSet.
	 */
	public ConsumableSet cost(){
		return cost;
	}

}
