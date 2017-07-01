package it.polimi.ingsw.ps45.model.cards;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * Venture Card - has two consumable costs and to consumable requirements because the requirements and the costs can differ and a list of end-game effects.
 * If the card has only one cost or only one requirements costOne and costTwo or reqOne and reqTwo will be the same. 
 */
public class Venture extends Card {

	
	private ArrayList<Effect> endGameEffects;
	
	private ConsumableSet costOne;
	private ConsumableSet costTwo;
	private ConsumableSet reqOne;
	private ConsumableSet reqTwo;
	
	/**
 	 * Constructor
 	 * @param e The era in which the card belongs to.
 	 * @param name The name of the card.
 	 * @param costOne the first cost of the card as a consumableset.
 	 * @param costTwo the second cost of the card as a consumableset.
 	 * @param reqOne the first requirement of the card as a consumableset.
 	 * @param reqTwo the second requirements of the card as a consumableset.
	 */
	public Venture(Era e, String name, ConsumableSet costOne, ConsumableSet costTwo, ConsumableSet reqOne, ConsumableSet reqTwo) {
		super(e, name);
		endGameEffects = new ArrayList<Effect>();
		
		this.costOne = costOne;
		this.costTwo = costTwo;
		this.reqOne = reqOne;
		this.reqTwo = reqTwo;
	}

	/**
	 * @return the first cost of the card as a ConsumableSet.
	 */
	public ConsumableSet costI(){
		return costOne;
	}
	/**
	 * @return the second cost of the card as a ConsumableSet.
	 */
	public ConsumableSet costII(){
		return costTwo;
	}
	
	/**
	 * @return the first requirements of the card as a ConsumableSet.
	 */
	public ConsumableSet requirementsI(){
		return reqOne;
	}
	
	/**
	 * @return the second requirements of the card as a ConsumableSet.
	 */
	public ConsumableSet requirementsII(){
		return reqTwo;
	}
	
	/**
	 * Adds an effect to the end-game effect list.
	 * @param e The effect to be added.
	 */
	public void addEndGameEffect(Effect e){
		endGameEffects.add(e);
	}

	/**
	 * @return A list of all end-game Effects.
	 */
	public ArrayList<Effect> getEndGameEffects() {
		return endGameEffects;
	}
	
	
	
}
