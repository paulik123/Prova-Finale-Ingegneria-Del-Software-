package it.polimi.ingsw.ps45.model.player;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.cards.Building;
import it.polimi.ingsw.ps45.model.cards.Territory;
import it.polimi.ingsw.ps45.model.cards.Venture;

public class ResourceSet {
	
	private ConsumableSet consumableSet;
	private ModifierSet<String> consumableModifierSet;
	private PawnSet pawnSet;
	private ModifierSet<PawnType> pawnModifierSet;
	private ModifierSet<String> actionModifierSet;
	private PermanentEffectSet permanentEffectSet;
	
	ArrayList<Territory> territoryList;
	ArrayList<Building> buildingList;
	ArrayList<Character> characterList;
	ArrayList<Venture> ventureList;
	
	
	public void collect(ConsumableSet cs){
		consumableSet.collect(consumableModifierSet, cs);
	}
	
	public void pay(RequirementsSet rSet, Pawn pawn){
		if(hasRequirements(rSet, pawn)){
			consumableSet.pay(rSet.getConsumableSet());
			pawn.setAvailable(false);
			pawn.setValue(0);
		}
	}
	
	private boolean hasRequirements(RequirementsSet rSet, Pawn pawn){
		if(!consumableSet.hasConsumablesAvailable(rSet.getConsumableSet())) return false;
		
		if(!(pawn.getValue() >= rSet.getPawnCost())) return false;
		
		return true;
	}
	
	public Pawn getPawn(PawnType type){
		return pawnSet.get(type);
	}
	
	public void increasePawnValue(Pawn p, int increase){
		int modifier = getActionModifier("servantIncreaseValue");
		if(modifier == 0) setActionModifier("servantPawnValue", 1);
		
		ConsumableSet cost = new ConsumableSet();
		cost.setConsumable("servants", increase*modifier);
		
		if(consumableSet.hasConsumablesAvailable(cost)){
			consumableSet.pay(cost);
			p.setValue(p.getValue() + increase);
		}
		
	}
	
	public int getPawnModifier(PawnType pt){
		return pawnModifierSet.getModifier(pt);
	}
	
	public void setModifierPawn(PawnType pt, int value){
		pawnModifierSet.addModifier(pt, value);
	}
	
	public int getActionModifier(String modifier){
		return actionModifierSet.getModifier(modifier);
	}
	
	public void setActionModifier(String modifier, int value){
		actionModifierSet.addModifier(modifier, value);
	}
	
	public boolean getPermanentEffect(String effect){
		return permanentEffectSet.getModifier(effect);
	}
	
	public void setPermanentEffect(String effect, boolean value){
		permanentEffectSet.addModifier(effect, value);
	}
	
	public void addTerritory(Territory t){
		territoryList.add(t);
	}
	public void addBuilding(Building t){
		buildingList.add(t);
	}
	public void addCharacter(Character t){
		characterList.add(t);
	}
	public void addVenture(Venture t){
		ventureList.add(t);
	}
	
	
	
}


