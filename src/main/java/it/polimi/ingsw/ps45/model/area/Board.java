package it.polimi.ingsw.ps45.model.area;

import it.polimi.ingsw.ps45.model.area.cardarea.*;

public class Board {
	ProductionAreaSmall productionAreaSmall;
	ProductionAreaBig productionAreaBig;
	
	HarvestAreaSmall harvestAreaSmall;
	HarvestAreaBig harvestAreaBig;
	
	CoinsMarketArea coinsMarketArea;
	ServantsMarketArea servantsMarketArea;
	MilitaryAndCoinArea militaryAndCoinArea;
	CouncilPrivilegeMarketArea councilPrivilegeMarketArea;
	
	CouncilPalaceArea councilPalaceArea;
	
	TerritoryTower territoryTower;
	CharacterTower characterTower;
	BuildingTower buildingTower;
	VentureTower ventureTower;
	
	public Board(){
		productionAreaSmall = new ProductionAreaSmall();
		productionAreaBig = new ProductionAreaBig();
		
		harvestAreaSmall = new HarvestAreaSmall();
		harvestAreaBig = new HarvestAreaBig();
		
		coinsMarketArea = new CoinsMarketArea();
		servantsMarketArea = new ServantsMarketArea();
		militaryAndCoinArea = new MilitaryAndCoinArea();
		councilPrivilegeMarketArea = new CouncilPrivilegeMarketArea();
		
		councilPalaceArea = new CouncilPalaceArea();
		
		territoryTower = new TerritoryTower();
		characterTower = new CharacterTower();
		buildingTower = new BuildingTower();
		ventureTower = new VentureTower();	
	}
	
}
