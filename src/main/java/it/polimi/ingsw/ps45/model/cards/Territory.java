package it.polimi.ingsw.ps45.model.cards;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * Territory Card - has a list of harvest effects. 
 */
public class Territory extends Card {
	
	private int harvestLevel;
	private ArrayList<Effect> harvestEffects;
	
	
	/**
 	 * Constructor
 	 * @param e The era in which the card belongs to.
 	 * @param name The name of the card.
 	 * @param harvestLevel the harvest level required for the harvest to work.
	 */
	public Territory(Era e, String name, int harvestLevel) {
		super(e, name);
		this.harvestLevel = harvestLevel;
		harvestEffects = new ArrayList<Effect>();
	}

	/**
	 * Runs the effects of the harvest.
	 * @param p The player that does the harvest.
	 */
	public void harvest(Player p){
		for(Effect e:harvestEffects){
			e.runEffect(p, 0);
		}
	}
	
	/**
	 * @return the harvestLevel(minimum level of the harvest that can activate the harvest effects of this card).
	 */
	public int getHarvestLevel(){
		return harvestLevel;
	}


	/**
	 * Adds an effect to the first harvest effect list.
	 * @param effect The effect to be added.
	 */
	public void addHarvestEffect(Effect effect){
		harvestEffects.add(effect);
	}

	/**
	 * @return A list of all harvest Effects.
	 */
	public ArrayList<Effect> getHarvestEffects() {
		return harvestEffects;
	}

	/**
	 * Replaces the current list of harvest effects with a new one.
	 * @param harvestEffects the new list of harvestEffects
	 */
	public void setHarvestEffects(ArrayList<Effect> harvestEffects) {
		this.harvestEffects = harvestEffects;
	}
	
	
}
