package it.polimi.ingsw.ps45.model.area;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import it.polimi.ingsw.ps45.model.area.cardarea.BuildingTower;
import it.polimi.ingsw.ps45.model.area.cardarea.CharacterTower;
import it.polimi.ingsw.ps45.model.area.cardarea.TerritoryTower;
import it.polimi.ingsw.ps45.model.area.cardarea.VentureCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.VentureTower;

public class Board {
	ProductionAreas productionAreas;
	
	HarvestAreas harvestAreas;
	
	NoCardArea coinsMarketArea;
	NoCardArea servantsMarketArea;
	NoCardArea militaryAndCoinArea;
	NoCardArea councilPrivilegeMarketArea;
	
	NoCardArea councilPalaceArea;
	
	TerritoryTower territoryTower;
	CharacterTower characterTower;
	BuildingTower buildingTower;
	VentureTower ventureTower;
	
	public Board(){
		productionAreas = new ProductionAreas();
		
		harvestAreas = new HarvestAreas();
		
		coinsMarketArea = loadFromFile("CoinsMarketArea.ser");
		servantsMarketArea = loadFromFile("ServantsMarketArea.ser");
		militaryAndCoinArea = loadFromFile("MilitaryAndCoinMarketArea.ser");
		councilPrivilegeMarketArea = loadFromFile("CouncilPrivilegeMarketArea.ser");
		
		councilPalaceArea = loadFromFile("CouncilPalaceArea.ser");
		
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

	public NoCardArea getCoinsMarketArea() {
		return coinsMarketArea;
	}

	public NoCardArea getServantsMarketArea() {
		return servantsMarketArea;
	}

	public NoCardArea getMilitaryAndCoinArea() {
		return militaryAndCoinArea;
	}

	public NoCardArea getCouncilPrivilegeMarketArea() {
		return councilPrivilegeMarketArea;
	}

	public NoCardArea getCouncilPalaceArea() {
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
	
	
	public static NoCardArea loadFromFile(String name){
		NoCardArea x = null;
        try {
	         FileInputStream fileIn = new FileInputStream("serialized//areas//" + name);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         x = (NoCardArea) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i) {
	         i.printStackTrace();
	      }catch(Exception ex) {
	         ex.printStackTrace();
	      }
        return x;
	}
	
}
