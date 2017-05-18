package it.polimi.ingsw.ps45.model.player;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.cards.Building;
import it.polimi.ingsw.ps45.model.cards.Territory;
import it.polimi.ingsw.ps45.model.cards.Venture;
import it.polimi.ingsw.ps45.model.cards.Character;

public class ResourceSet {
	
	private ConsumableSet resources;
	
	private ConsumableSet territoryActionDiscount;
	private ConsumableSet characterActionDiscount;
	private ConsumableSet buildingActionDiscount;
	private ConsumableSet ventureActionDiscount;
	
	private ConsumableSet collectPenalty;
	
	private PawnValueModifier pawnValueModifier;
	
	private ActionValueModifier actionValueModifiers;
	
	private PermanentEffects permanentEffects;
	
	private PawnSet pawnSet;
	
	ArrayList<Territory> territoryList;
	ArrayList<Building> buildingList;
	ArrayList<Character> characterList;
	ArrayList<Venture> ventureList;
	
	
	public void collect(ConsumableSet cs){
		cs.makeDiscount(collectPenalty);
		resources.collect(cs);
	}
	
	
	public void pay(ConsumableSet c, Pawn pawn){
			resources.pay(c);
			pawn.setAvailable(false);
			pawn.setValue(0);
	}	
	
	public boolean hasConsumables(ConsumableSet cSet){
		return resources.hasConsumablesAvailable(cSet);
	}
	
	public void setPawn(PawnType pt, int value, boolean b){
		pawnSet.set(pt, value, b);
	}
	
	public Pawn getPawn(PawnType type){
		return pawnSet.get(type);
	}
	
	//Return the value of the pawn + modifiers
	public int getPawnValue(PawnType pt){
		return pawnSet.get(pt).getValue() + pawnValueModifier.getValue(pt);
	}
	
	
	public int getPawnModifier(PawnType pt){
		return pawnValueModifier.getValue(pt);
	}
	
	public void setModifierPawn(PawnType pt, int value){
		pawnValueModifier.setValue(pt, value);
	}
	
	public ConsumableSet getTerritoryActionDiscount(){
		return territoryActionDiscount;
	}
	
	public ConsumableSet getCharacterActionDiscount(){
		return characterActionDiscount;
	}
	
	public ConsumableSet getBuildingActionDiscount(){
		return buildingActionDiscount;
	}
	
	public ConsumableSet getVentureActionDiscount(){
		return ventureActionDiscount;
	}
	
	public ArrayList<Territory> getTerritoryList() {
		return territoryList;
	}

	public ArrayList<Building> getBuildingList() {
		return buildingList;
	}


	public ArrayList<Character> getCharacterList() {
		return characterList;
	}

	public ArrayList<Venture> getVentureList() {
		return ventureList;
	}
}


