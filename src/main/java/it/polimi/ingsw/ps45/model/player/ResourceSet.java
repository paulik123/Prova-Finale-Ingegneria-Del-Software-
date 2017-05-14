package it.polimi.ingsw.ps45.model.player;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.cards.Building;
import it.polimi.ingsw.ps45.model.cards.Territory;

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
	
	public boolean hasRequirements(RequirementsSet rSet, PawnType pawnType){
		if(!consumableSet.hasConsumablesAvailable(rSet.getConsumableSet())) return false;
		
		int pawnValue = pawnSet.get(pawnType).getValue();
		if(!(pawnValue >= rSet.getPawnCost())) return false;
		
		return true;
	}
	
	public void increasePawnValue(Pawn p, int increase){
		int modifier = actionModifierSet.getModifier("servantIncreaseValue");
		if(modifier == 0) actionModifierSet.addModifier("servantPawnValue", 1);
		
		ConsumableSet cost = new ConsumableSet();
		cost.setConsumable("servants", increase*modifier);
		
		if(consumableSet.hasConsumablesAvailable(cost)){
			consumableSet.pay(cost);
			p.setValue(p.getValue() + increase);
		}
		
	}
	
	
	
}


