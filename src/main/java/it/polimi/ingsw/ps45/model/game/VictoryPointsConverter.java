package it.polimi.ingsw.ps45.model.game;

import it.polimi.ingsw.ps45.model.cards.Building;
import it.polimi.ingsw.ps45.model.cards.Venture;
import it.polimi.ingsw.ps45.model.effects.Effect;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;
import it.polimi.ingsw.ps45.model.player.Player;
import it.polimi.ingsw.ps45.model.vatican.Vatican;
import it.polimi.ingsw.ps45.model.vatican.VaticanVictoryPointsConverter;

/**
 * Converter class which converts various consumables and resources into victoryPoints for the end-game calculation.
 */
public class VictoryPointsConverter {
	
	private Player p;
	
	/**
 	 * Constructor
 	 * @param p the player which will collect the victory points.
	 */
	public VictoryPointsConverter(Player p){
		this.p = p;
	}
	
	/**
 	 * Awards the player with victory points depending on how many characters he "owns".
	 */
	public void characterVictoryPoints(){
		if(p.getResourceSet().getPermanentEffects().isNoCharacterVictoryPoints()) return;
		int c = p.getResourceSet().getCharacterList().size();
		int victoryPoints = 0;
		
		switch(c){
		case 1:
			victoryPoints = 1;
			break;
		case 2:
			victoryPoints = 3;
			break;
		case 3:
			victoryPoints = 6;
			break;
		case 4:
			victoryPoints = 10;
			break;
		case 5:
			victoryPoints = 15;
			break;
		case 6:
			victoryPoints = 21;
			break;
		default:
			victoryPoints = 0;
			break;
		}
		ConsumableSet cs = new ConsumableSet();
		cs.setVictoryPoints(victoryPoints);
		p.getResourceSet().collect(cs);
	}
	
	/**
 	 * Awards the player with victory points depending on how many territories he "owns".
	 */
	public void territoryVictoryPoints(){
		if(p.getResourceSet().getPermanentEffects().isNoTerritoryVictoryPoints()) return;
		int t = p.getResourceSet().getTerritoryList().size();
		int victoryPoints = 0;
		
		switch(t){
		case 3:
			victoryPoints = 1;
			break;
		case 4:
			victoryPoints = 4;
			break;
		case 5:
			victoryPoints = 10;
			break;
		case 6:
			victoryPoints = 20;
			break;
		default:
			victoryPoints = 0;
			break;
		}
		ConsumableSet cs = new ConsumableSet();
		cs.setVictoryPoints(victoryPoints);
		p.getResourceSet().collect(cs);
	}
	
	/**
 	 * Applies the end-game effect of all ventures cards the player owns to himself if he's eligible to receive them.
	 */
	public void applyVentureVictoryPoints(){
		if(p.getResourceSet().getPermanentEffects().isNoVentureVictoryPoints()) return;
		for(Venture v:p.getResourceSet().getVentureList()){
			for(Effect e:v.getEndGameEffects()){
				e.runEffect(p, 0);
			}
		}
	}
	
	/**
 	 * Awards the player with victory points depending on how many faith points he has.
	 */
	public void vaticanVictoryPoints(){
		VaticanVictoryPointsConverter vpc = Vatican.converter;
		int victoryPoints = vpc.getVictoryPoints(p.getResourceSet().getResources().getFaithPoints());
		
		ConsumableSet cs = new ConsumableSet();
		cs.setVictoryPoints(victoryPoints);
		p.getResourceSet().collect(cs);
	}
	
	/**
 	 * Awards the player with victory points depending on how many wood/stone/coins/servants he has.
 	 * It it currently the sum/5.
	 */
	
	public void resourceVictoryPoints(){
		int resources = 0;
		resources = resources + p.getResourceSet().getResources().getWood();
		resources = resources + p.getResourceSet().getResources().getStone();
		resources = resources + p.getResourceSet().getResources().getCoins();
		resources = resources + p.getResourceSet().getResources().getServants();
		
		ConsumableSet cs = new ConsumableSet();
		cs.setVictoryPoints(resources / 5);
		p.getResourceSet().collect(cs);
	}
	
	/**
 	 * Removes a fifth of the players end-game victory points if he suffers from that penalty.
	 */
	public void aFifthVictoryPointsPenalty(){
		if(!p.getResourceSet().getPermanentEffects().isaFifthVictoryPointsPenalty()) return;
		ConsumableSet cs = new ConsumableSet();
		cs.setVictoryPoints(p.getResourceSet().getResources().getVictoryPoints() / 5);
		p.getResourceSet().getResources().pay(cs);
	}
	
	/**
 	 * Removes victory points from the player based on how many military points he has if he suffers from that penalty.
	 */
	public void militaryPointsPenalty(){
		if(!p.getResourceSet().getPermanentEffects().isMilitaryPointsVictoryPointsPenalty()) return;
		ConsumableSet cs = new ConsumableSet();
		cs.setVictoryPoints(p.getResourceSet().getResources().getMilitaryPoins());
		p.getResourceSet().getResources().pay(cs);
	}
	
	/**
 	 * Removes victory points from the player based on how many resources he has if he suffers from that penalty.
	 */
	public void resourcePointsPenalty(){
		if(!p.getResourceSet().getPermanentEffects().isResourceVictoryPointsPenalty()) return;
		int resources = 0;
		resources = resources + p.getResourceSet().getResources().getWood();
		resources = resources + p.getResourceSet().getResources().getStone();
		resources = resources + p.getResourceSet().getResources().getCoins();
		resources = resources + p.getResourceSet().getResources().getServants();
		ConsumableSet cs = new ConsumableSet();
		cs.setVictoryPoints(resources);
		p.getResourceSet().getResources().pay(cs);
	}
	
	/**
 	 * Removes victory points from the player based on how wood/stone his buildings costif he suffers from that penalty.
	 */
	public void buildingPointsPenalty(){
		if(!p.getResourceSet().getPermanentEffects().isBuildingCostVictoryPointsPenalty()) return;
		int cost = 0;
		for(Building b:p.getResourceSet().getBuildingList()){
			cost = cost + b.cost().getWood();
			cost = cost + b.cost().getStone();
		}
		ConsumableSet cs = new ConsumableSet();
		cs.setVictoryPoints(cost);
		p.getResourceSet().getResources().pay(cs);
	}

}
