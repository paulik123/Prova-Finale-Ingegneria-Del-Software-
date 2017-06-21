package it.polimi.ingsw.ps45.model.player;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.polimi.ingsw.ps45.gson.GsonWithInterface;
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
	
	private PersonalBonusTile bonusTile;
	


	private PawnSet pawnSet;
	
	ArrayList<Territory> territoryList;
	ArrayList<Building> buildingList;
	ArrayList<Character> characterList;
	ArrayList<Venture> ventureList;
	
	
	public ResourceSet(ConsumableSet initialResources, String bonusTile){
		this.resources = initialResources;
		
		territoryList = new ArrayList<Territory>();
		buildingList = new ArrayList<Building>();
		characterList = new ArrayList<Character>();
		ventureList = new ArrayList<Venture>();
		
		this.territoryActionDiscount = new ConsumableSet();
		this.characterActionDiscount = new ConsumableSet();
		this.buildingActionDiscount = new ConsumableSet();
		this.ventureActionDiscount = new ConsumableSet();
		
		this.pawnSet = new PawnSet();
		
		this.collectPenalty = new ConsumableSet();
		
		this.pawnValueModifier = new PawnValueModifier();
		
		this.actionValueModifier = new ActionValueModifier();
		
		this.permanentEffects = new PermanentEffects();
		
		initializeBonusTile(bonusTile);
		
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
		if(permanentEffects.isValueFivePawn()){
			pawnSet.set(pt, 5, b);
			return;
		}
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


	public PersonalBonusTile getBonusTile() {
		return bonusTile;
	}


	public ConsumableSet getCollectPenalty() {
		return collectPenalty;
	}


	public ActionValueModifier getActionValueModifier() {
		return actionValueModifier;
	}


	public PawnSet getPawnSet() {
		return pawnSet;
	}
	
	public void initializeBonusTile(String bt){
		Gson gson = GsonWithInterface.getGson();
		
		
		try {
			bonusTile = gson.fromJson(new FileReader("serialized\\bonustiles\\" + bt  + ".json"), PersonalBonusTile.class);
		} catch (Exception e) {
			try {
				bonusTile = gson.fromJson(new FileReader("serialized\\bonustiles\\0.json"), PersonalBonusTile.class);
			} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	
	
	
	
	
	
	

	
}


