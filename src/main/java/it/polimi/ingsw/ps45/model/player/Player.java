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
	
	
	public void addCard(Territory c){
		resourceSet.getTerritoryList().add(c);
	}
	public void addCard(Building c){
		resourceSet.getBuildingList().add(c);
	}
	public void addCard(Character c){
		resourceSet.getCharacterList().add(c);
	}
	public void addCard(Venture c){
		resourceSet.getVentureList().add(c);
	}
	
	
}
