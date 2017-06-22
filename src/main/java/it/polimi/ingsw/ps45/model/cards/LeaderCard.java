package it.polimi.ingsw.ps45.model.cards;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

public class LeaderCard {
	
	private boolean usedEffectThisRound;
	
	private String name;
	
	private ConsumableSet resourceCost;
	private int territoryCost;
	private int characterCost;
	private int buildingCost;
	private int ventureCost;
	private int anyCardCost;
	
	private ArrayList<Effect> immediateEffects;
	private ArrayList<Effect> perRoundEffects;
	
	public LeaderCard(String name, ConsumableSet cs, int territoryCost, int characterCost, int buildingCost, int ventureCost, int anyCardCost){
		this.name = name;
		this.resourceCost = cs;
		this.territoryCost = territoryCost;
		this.characterCost = characterCost;
		this.buildingCost = buildingCost;
		this.ventureCost = ventureCost;
		
		immediateEffects = new ArrayList<Effect>();
		perRoundEffects = new ArrayList<Effect>();
	}
	
	public void immediateEffect(Player p){
		for(Effect e:immediateEffects){
			e.runEffect(p, 0);
		}
	}
	
	public void perRoundEffect(Player p){
		for(Effect e:perRoundEffects){
			e.runEffect(p, 0);
		}
		usedEffectThisRound = true;
	}
	
	public void addImmediateEffect(Effect effect) {
		immediateEffects.add(effect);
	}
	
	public void addPerRoundEffect(Effect effect){
		perRoundEffects.add(effect);
	}

	public boolean usedEffectThisRound() {
		return usedEffectThisRound;
	}

	public void setUsedEffectThisRound(boolean usedEffectThisRound) {
		this.usedEffectThisRound = usedEffectThisRound;
	}
	
	public boolean canActivate(Player p){
		if(		
				p.getResourceSet().hasConsumables(resourceCost) &&
				p.getResourceSet().getCharacterList().size() >= characterCost &&
				p.getResourceSet().getTerritoryList().size() >= territoryCost &&
				p.getResourceSet().getBuildingList().size() >= buildingCost &&
				p.getResourceSet().getVentureList().size() >= ventureCost &&
				hasAnyCardCost(p)
		   ) return true;
		else return false;
	}
	
	private boolean hasAnyCardCost(Player p){
		return  p.getResourceSet().getTerritoryList().size() >= anyCardCost ||
				p.getResourceSet().getCharacterList().size() >= anyCardCost ||
				p.getResourceSet().getBuildingList().size() >= anyCardCost ||
				p.getResourceSet().getVentureList().size() >= anyCardCost;
	}

	public String getName() {
		return name;
	}


}
