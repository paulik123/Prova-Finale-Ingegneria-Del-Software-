package it.polimi.ingsw.ps45.model.player;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.cards.Building;
import it.polimi.ingsw.ps45.model.cards.Character;
import it.polimi.ingsw.ps45.model.cards.Territory;
import it.polimi.ingsw.ps45.model.cards.Venture;

public class ResourceSet {
	
	private ConsumableSet resources;
	
	private ConsumableSet territoryActionDiscount;
	private ConsumableSet characterActionDiscount;
	private ConsumableSet buildingActionDiscount;
	private ConsumableSet ventureActionDiscount;
	
	private ConsumableSet collectPenalty;
	
	private PawnValueModifier pawnValueModifier;
	
	private ActionValueModifier actionValueModifier;
	
	private PermanentEffects permanentEffects;
	
	private BonusTile bonusTile;
	


	private PawnSet pawnSet;
	
	ArrayList<Territory> territoryList;
	ArrayList<Building> buildingList;
	ArrayList<Character> characterList;
	ArrayList<Venture> ventureList;
	
	
	public ResourceSet(ConsumableSet initialResources){
		this.resources = initialResources;
		
		this.territoryActionDiscount = new ConsumableSet();
		this.characterActionDiscount = new ConsumableSet();
		this.buildingActionDiscount = new ConsumableSet();
		this.ventureActionDiscount = new ConsumableSet();
		
		this.collectPenalty = new ConsumableSet();
		
		this.pawnValueModifier = new PawnValueModifier();
		
		this.actionValueModifier = new ActionValueModifier();
		
		this.permanentEffects = new PermanentEffects();
		
		this.bonusTile = new DefaultBonusTile();
		
	}
	
	
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
	
	public PermanentEffects getPermanentEffects() {
		return permanentEffects;
	}


	public ConsumableSet getResources() {
		return resources;
	}


	public BonusTile getBonusTile() {
		return bonusTile;
	}


	public ConsumableSet getCollectPenalty() {
		return collectPenalty;
	}


	public ActionValueModifier getActionValueModifier() {
		return actionValueModifier;
	}
	
	
	
	
	
	

	
}


