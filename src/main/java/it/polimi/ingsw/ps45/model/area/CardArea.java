package it.polimi.ingsw.ps45.model.area;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.cards.Card;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;
import it.polimi.ingsw.ps45.model.player.RequirementsSet;

public class CardArea<T extends Card> extends Area {
	
	T card;
	ConsumableSet immediateEffectCS;
	
	public CardArea(T card, ConsumableSet cs, int cost){
		occupiedBy = new ArrayList<PlayerPawnPair>();
		maxOccupants = 1;
		
		this.card = card;
		this.immediateEffectCS = cs;
		this.setCost(cost);
	}
	
	public T getCard() {
		return card;
	}

	public void setCard(T card) {
		this.card = card;
	}

	protected void removeCard(){
		card = null;
	}
	
	public RequirementsSet getRequirements(){
		return new RequirementsSet(card.cost(), this.getCost());
	}

}

//TODO: 3 Coins cost if card area is occupied
