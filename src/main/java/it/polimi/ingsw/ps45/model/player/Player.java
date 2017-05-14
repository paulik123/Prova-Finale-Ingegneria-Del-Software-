package it.polimi.ingsw.ps45.model.player;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.cards.Building;
import it.polimi.ingsw.ps45.model.cards.Territory;
import it.polimi.ingsw.ps45.model.cards.Venture;

public class Player {
	private ResourceSet resourceSet;
	
	public ResourceSet getResourceSet(){
		return resourceSet;
	}
	
	public void harvest(Pawn p, int servantsAdded){
		resourceSet.increasePawnValue(p, servantsAdded);
		
		int level = p.getValue() + resourceSet.getPawnModifier(p.getType()) + resourceSet.getActionModifier("production");
	
		ArrayList<Territory> list = resourceSet.getTerritoryList();
		for(Territory t:list){
			if(level>= t.getLevel())t.harvest(this);
		}
	}
	
	public void production(Pawn p, int servantsAdded){
		resourceSet.increasePawnValue(p, servantsAdded);
		
		int level = p.getValue() + resourceSet.getPawnModifier(p.getType()) + resourceSet.getActionModifier("production");
		
		ArrayList<Building> list = resourceSet.getBuildingList();
		for(Building b:list){
			if(level >= b.getLevel())b.production(this);
		}
	}
	
	public void getCard(Territory c){
		resourceSet.getTerritoryList().add(c);
	}
	public void getCard(Building c){
		resourceSet.getBuildingList().add(c);
	}
	public void getCard(Character c){
		resourceSet.getCharacterList().add(c);
	}
	public void getCard(Venture c){
		resourceSet.getVentureList().add(c);
	}
	
	
}
