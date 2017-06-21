package it.polimi.ingsw.ps45.model.cards;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

public class Building extends Card {
	
	ArrayList<Effect> productionIEffects;
	ArrayList<Effect> productionIIEffects;
	
	ConsumableSet cost;
	
	
	public Building(Era e, String name, int productionLevel, ConsumableSet cost) {
		super(e, name);
		this.productionLevel = productionLevel;
		
		productionIEffects = new ArrayList<Effect>();
		productionIIEffects = new ArrayList<Effect>();
		
		this.cost = cost;
	}
	public ConsumableSet cost() {
		return cost;
	}
	public void productionI(Player p) {
		for(Effect e:productionIEffects){
			e.runEffect(p, 0);
		}
	}
	public void productionII(Player p) {
		for(Effect e:productionIIEffects){
			e.runEffect(p, 0);
		}
	}
	
	private int productionLevel;
	public int getProductionLevel(){
		return productionLevel;
	}
	
	public void addProductionIEffect(Effect e){
		productionIEffects.add(e);
	}
	
	public void addProductionIIEffect(Effect e){
		productionIIEffects.add(e);
	}
	
}
