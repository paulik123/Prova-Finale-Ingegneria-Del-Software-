package it.polimi.ingsw.ps45.model.cards;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.player.Player;

public class Territory extends Card {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9088028847025402303L;
	private int harvestLevel;
	private ArrayList<Effect> harvestEffects;
	
	
	public Territory(Era e, String name, int harvestLevel) {
		super(e, name);
		this.harvestLevel = harvestLevel;
		harvestEffects = new ArrayList<Effect>();
	}

	public void harvest(Player p){
		for(Effect e:harvestEffects){
			e.runEffect(p, 0);
		}
	}
	
	public int getHarvestLevel(){
		return harvestLevel;
	}


	
	public void addHarvestEffect(Effect effect){
		harvestEffects.add(effect);
	}

	public ArrayList<Effect> getHarvestEffects() {
		return harvestEffects;
	}

	public void setHarvestEffects(ArrayList<Effect> harvestEffects) {
		this.harvestEffects = harvestEffects;
	}
	
	
}
