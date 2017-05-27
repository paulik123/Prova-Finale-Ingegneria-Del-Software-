package it.polimi.ingsw.ps45.model.area;

import java.io.FileNotFoundException;
import java.io.FileReader;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.polimi.ingsw.ps45.gson.PropertyBasedInterfaceMarshal;
import it.polimi.ingsw.ps45.model.area.cardarea.BuildingTower;
import it.polimi.ingsw.ps45.model.area.cardarea.CharacterTower;
import it.polimi.ingsw.ps45.model.area.cardarea.TerritoryTower;
import it.polimi.ingsw.ps45.model.area.cardarea.VentureTower;
import it.polimi.ingsw.ps45.model.effects.Effect;

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
		
		coinsMarketArea = loadFromFile("serialized//areas//CoinsMarketArea.json");
		servantsMarketArea = loadFromFile("serialized//areas//ServantsMarketArea.json");
		militaryAndCoinArea = loadFromFile("serialized//areas//MilitaryAndCoinsMarketArea.json");
		councilPrivilegeMarketArea = loadFromFile("serialized//areas//CouncilPrivilegeMarketArea.json");
		
		councilPalaceArea = loadFromFile("serialized//areas//CouncilPalaceArea.json");
		
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
	
	
	public static NoCardArea loadFromFile(String path){
		Gson gson = new GsonBuilder()
                .registerTypeAdapter(Effect.class,
                        new PropertyBasedInterfaceMarshal()).create();
	 
		NoCardArea c = null;
	try {
		c = gson.fromJson(new FileReader(path), NoCardArea.class);
	} catch (JsonSyntaxException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JsonIOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 return c;
	}

}
