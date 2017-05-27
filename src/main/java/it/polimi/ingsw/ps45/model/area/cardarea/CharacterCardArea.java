package it.polimi.ingsw.ps45.model.area.cardarea;

import java.io.Serializable;

import it.polimi.ingsw.ps45.model.area.Area;
import it.polimi.ingsw.ps45.model.cards.Character;
import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.player.Player;

public class CharacterCardArea extends Area implements Serializable{
	Effect effect;
	
	public CharacterCardArea(int cost, Effect effect, String name){
		super();
		super.setName(name);
		this.setCost(cost);
		this.effect = effect;
		this.maxOccupants = 1;
	}
	
	private Character c;

	public Character getCharacter() {
		return c;
	}

	public void setCharacter(Character c) {
		this.c = c;
	}

	@Override
	public void immediateEffect(Player p, int value) {
		effect.runEffect(p, value);
	}
	
}
