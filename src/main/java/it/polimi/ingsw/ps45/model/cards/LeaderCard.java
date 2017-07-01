package it.polimi.ingsw.ps45.model.cards;

import java.util.ArrayList;

import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;

/**
 * Leader Card - has a consumable cost also has requirements regarding the number of card the player who activates it owns. 
 * It also can have immediate effects or per-round effects.
 */
public class LeaderCard {
	
	private boolean usedEffectThisRound;
	
	private String name;
	
	private ConsumableSet resourceCost;
	private int territoryCost;
	private int characterCost;
	private int buildingCost;
	private int ventureCost;
	private int anyCardCost;
	
	private ArrayList<Effect> immediateEffects;
	private ArrayList<Effect> perRoundEffects;
	
	/**
 	 * Constructor
 	 * @param name The name of the card.
 	 * @param cs the cost of the card as a consumableset.
 	 * @param territoryCost the number minimum of territory cards the player must have in order to activate this leader card.
 	 * @param characterCost the number minimum of character cards the player must have in order to activate this leader card.
 	 * @param buildingCost the number minimum of building cards the player must have in order to activate this leader card.
 	 * @param ventureCost the number minimum of venture cards the player must have in order to activate this leader card.
 	 * @param anyCardCost the number minimum of any type cards the player must have in order to activate this leader card.
	 */
	public LeaderCard(String name, ConsumableSet cs, int territoryCost, int characterCost, int buildingCost, int ventureCost, int anyCardCost){
		this.name = name;
		this.resourceCost = cs;
		this.territoryCost = territoryCost;
		this.characterCost = characterCost;
		this.buildingCost = buildingCost;
		this.ventureCost = ventureCost;
		
		immediateEffects = new ArrayList<Effect>();
		perRoundEffects = new ArrayList<Effect>();
	}
	
	/**
	 * Applies the immediate effect of the card to the player
	 * @param p The effect that will be applied.
	 */
	public void immediateEffect(Player p){
		for(Effect e:immediateEffects){
			e.runEffect(p, 0);
		}
	}
	
	/**
	 * Applies the per-round effect of the card to the player
	 * Also signals that the per-round effects have been used this turn so they can't be used again.
	 * @param p The effect that will be applied.
	 */
	public void perRoundEffect(Player p){
		for(Effect e:perRoundEffects){
			e.runEffect(p, 0);
		}
		usedEffectThisRound = true;
	}
	
	/**
	 * Adds an effect to the immediateEffect list.
	 * @param the effect to be added.
	 */
	public void addImmediateEffect(Effect effect) {
		immediateEffects.add(effect);
	}
	
	/**
	 * Adds an effect to the per-round Effect list.
	 * @param the effect to be added.
	 */
	public void addPerRoundEffect(Effect effect){
		perRoundEffects.add(effect);
	}

	/**
	 * @return true of the per-round effects have already been used this round.
	 */
	public boolean usedEffectThisRound() {
		return usedEffectThisRound;
	}

	/**
	 * Sets the usedEffectThisRoundBoolean to a new value
	 * Is used by the game to set it to false every time a new round starts so the player can use the effect again.
	 * @param usedEffectThisRound the boolean value that will be set.
	 */
	public void setUsedEffectThisRound(boolean usedEffectThisRound) {
		this.usedEffectThisRound = usedEffectThisRound;
	}
	
	/**
	 * @param p the player that tries to activate the card.
	 * @return true if the player meets all the necessary requirements.
	 */
	public boolean canActivate(Player p){
		if(		
				p.getResourceSet().hasConsumables(resourceCost) &&
				p.getResourceSet().getCharacterList().size() >= characterCost &&
				p.getResourceSet().getTerritoryList().size() >= territoryCost &&
				p.getResourceSet().getBuildingList().size() >= buildingCost &&
				p.getResourceSet().getVentureList().size() >= ventureCost &&
				hasAnyCardCost(p)
		   ) return true;
		else return false;
	}
	
	/**
	 * @return true if the number of at least a type of card that the player owns meets the minimum requirements.
	 */
	private boolean hasAnyCardCost(Player p){
		return  p.getResourceSet().getTerritoryList().size() >= anyCardCost ||
				p.getResourceSet().getCharacterList().size() >= anyCardCost ||
				p.getResourceSet().getBuildingList().size() >= anyCardCost ||
				p.getResourceSet().getVentureList().size() >= anyCardCost;
	}

	/**
	 * @return the name of the leader card.
	 */
	public String getName() {
		return name;
	}


}
