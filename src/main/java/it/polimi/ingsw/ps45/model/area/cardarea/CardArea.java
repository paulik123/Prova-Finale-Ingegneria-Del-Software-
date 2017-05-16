package it.polimi.ingsw.ps45.model.area.cardarea;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.area.Area;
import it.polimi.ingsw.ps45.model.cards.Card;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;


public abstract class CardArea<T extends Card> extends Area {
	
	T card;
	
	
	
	public CardArea(T t){
		super();
		card = t;
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
}

//TODO: 3 Coins cost if card area is occupied
