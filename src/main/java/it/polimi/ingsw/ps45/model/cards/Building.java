package it.polimi.ingsw.ps45.model.cards;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * Building Card - has a consumable cost and production functionality. 
 */

public class Building extends Card {
	
	private ArrayList<Effect> productionIEffects;
	private ArrayList<Effect> productionIIEffects;
	
	private ConsumableSet cost;
	private int productionLevel;
	
	
	/**
 	 * Constructor
 	 * @param e The era in which the card belongs to.
 	 * @param name The name of the card.
 	 * @param productionLevel the production level required for the production to work.
 	 * @param cost the cost of the card as a consumableset.
	 */
	public Building(Era e, String name, int productionLevel, ConsumableSet cost) {
		super(e, name);
		this.productionLevel = productionLevel;
		
		productionIEffects = new ArrayList<Effect>();
		productionIIEffects = new ArrayList<Effect>();
		
		this.cost = cost;
	}
	
	/**
	 * @return the cost of the card as a ConsumableSet.
	 */
	public ConsumableSet cost() {
		return cost;
	}
	
	/**
	 * Runs the effect of the first production mode.
	 * @param p The player that does the production.
	 */
	public void productionI(Player p) {
		for(Effect e:productionIEffects){
			e.runEffect(p, 0);
		}
	}
	
	/**
	 * Runs the effect of the second production mode.
	 * @param p The player that does the production.
	 */
	public void productionII(Player p) {
		for(Effect e:productionIIEffects){
			e.runEffect(p, 0);
		}
	}
	
	/**
	 * @return the productionLevel(minimum level of the production that can activate the production effects of this card).
	 */
	public int getProductionLevel(){
		return productionLevel;
	}
	
	/**
	 * Adds an effect to the first production effect list.
	 * @param e The effect to be added.
	 */
	public void addProductionIEffect(Effect e){
		productionIEffects.add(e);
	}
	
	/**
	 * Adds an effect to the second production effect list.
	 * @param e The effect to be added.
	 */
	public void addProductionIIEffect(Effect e){
		productionIIEffects.add(e);
	}
	
}
