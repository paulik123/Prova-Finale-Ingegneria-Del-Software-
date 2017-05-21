package it.polimi.ingsw.ps45.model.cards;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

public class Venture extends Card {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3130752413305105553L;
	
	ArrayList<Effect> endGameEffects;
	
	ConsumableSet costOne;
	ConsumableSet costTwo;
	ConsumableSet reqOne;
	ConsumableSet reqTwo;
	
	public Venture(Era e, String name, ConsumableSet costOne, ConsumableSet costTwo, ConsumableSet reqOne, ConsumableSet reqTwo) {
		super(e, name);
		endGameEffects = new ArrayList<Effect>();
		
		this.costOne = costOne;
		this.costTwo = costTwo;
		this.reqOne = reqOne;
		this.reqTwo = reqTwo;
	}

	public ConsumableSet costI(){
		return costOne;
	}
	public ConsumableSet costII(){
		return costTwo;
	}
	public ConsumableSet requirementsI(){
		return reqOne;
	}
	public ConsumableSet requirementsII(){
		return reqTwo;
	}
	@Override
	public void immediateEffect(Player p) {
		for(Effect e:super.getEffects()){
			e.runEffect(p, 0);
		}
	}
	
	public void addEndGameEffect(Effect e){
		endGameEffects.add(e);
	}
	
}
