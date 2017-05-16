package it.polimi.ingsw.ps45.model.area;

import java.util.HashMap;

import it.polimi.ingsw.ps45.model.cards.Building;
import it.polimi.ingsw.ps45.model.cards.Character;
import it.polimi.ingsw.ps45.model.cards.Territory;
import it.polimi.ingsw.ps45.model.cards.Venture;
import it.polimi.ingsw.ps45.model.player.ConsumableSet;

public class Board {
	private static int marketCoinsReward = 5;
	private static int marketServantsReward = 5;
	private static int marketMilitaryReward = 3;
	private static int marketMilitaryCoinsReward = 2;
	private static int marketCost = 1;
	
	private static int councilPalaceCost = 1;
	
	
	public Board(){
		councilPalaceMap.put("councilPalace", new CouncilPalaceArea(councilPalaceCost));
		marketMap.put("coinsMarket", newCoinsMarket());
		marketMap.put("servantsMarket", newServantsMarket());
		
	}
	
	public HashMap<String, CouncilPalaceArea> councilPalaceMap;
	public HashMap<String, MarketArea> marketMap;
	public HashMap<String, ProductionArea> productionMap;
	public HashMap<String, HarvestArea> harvestMap;
	public HashMap<String, CardArea<Territory>> territoryTower;
	public HashMap<String, CardArea<Character>> characterTower;
	public HashMap<String, CardArea<Venture>> ventureTower;
	public HashMap<String, CardArea<Building>> buildingTower;
	
	
	private MarketArea newCoinsMarket(){
		ConsumableSet cs = new ConsumableSet();
		cs.setConsumable("coins", marketCoinsReward);
		return new MarketArea(cs, marketCost);
	}
	
	private MarketArea newServantsMarket(){
		ConsumableSet cs = new ConsumableSet();
		cs.setConsumable("servants", marketServantsReward);
		return new MarketArea(cs, marketCost);
	}
	
	private MarketArea newMilitaryMarket(){
		ConsumableSet cs = new ConsumableSet();
		cs.setConsumable("militaryPoins", marketMilitaryReward);
		cs.setConsumable("coins", marketMilitaryCoinsReward);
		return new MarketArea(cs, marketCost);
	}
	
	
}
