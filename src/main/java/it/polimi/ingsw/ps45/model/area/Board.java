package it.polimi.ingsw.ps45.model.area;

import it.polimi.ingsw.ps45.model.area.cardarea.BuildingTower;
import it.polimi.ingsw.ps45.model.area.cardarea.CharacterTower;
import it.polimi.ingsw.ps45.model.area.cardarea.TerritoryTower;
import it.polimi.ingsw.ps45.model.area.cardarea.VentureTower;

public class Board {
	ProductionAreas productionAreas;
	
	HarvestAreas harvestAreas;
	
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
		productionAreas = new ProductionAreas();
		
		harvestAreas = new HarvestAreas();
		
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

	public ProductionAreas getProductionAreas() {
		return productionAreas;
	}

	public HarvestAreas getHarvestAreas() {
		return harvestAreas;
	}

	public CoinsMarketArea getCoinsMarketArea() {
		return coinsMarketArea;
	}

	public ServantsMarketArea getServantsMarketArea() {
		return servantsMarketArea;
	}

	public MilitaryAndCoinArea getMilitaryAndCoinArea() {
		return militaryAndCoinArea;
	}

	public CouncilPrivilegeMarketArea getCouncilPrivilegeMarketArea() {
		return councilPrivilegeMarketArea;
	}

	public CouncilPalaceArea getCouncilPalaceArea() {
		return councilPalaceArea;
	}

	public TerritoryTower getTerritoryTower() {
		return territoryTower;
	}

	public CharacterTower getCharacterTower() {
		return characterTower;
	}

	public BuildingTower getBuildingTower() {
		return buildingTower;
	}

	public VentureTower getVentureTower() {
		return ventureTower;
	}
	
	
	
	
}
