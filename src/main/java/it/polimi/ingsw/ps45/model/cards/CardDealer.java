package it.polimi.ingsw.ps45.model.cards;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.polimi.ingsw.ps45.gson.PropertyBasedInterfaceMarshal;
import it.polimi.ingsw.ps45.model.area.Board;
import it.polimi.ingsw.ps45.model.area.cardarea.BuildingCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.CharacterCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.TerritoryCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.VentureCardArea;
import it.polimi.ingsw.ps45.model.effects.Effect;

public class CardDealer {
	
	private static final String territoryPath = "serialized//cards//territories//";
	private static final String characterPath = "serialized//cards//characters//";
	private static final String buildingPath = "serialized//cards//buildings//";
	private static final String venturePath = "serialized//cards//ventures//";
	
	
	
	HashMap<Era, ArrayList<Territory>> territoryList;
	HashMap<Era, ArrayList<Character>> characterList;
	HashMap<Era, ArrayList<Building>> buildingList;
	HashMap<Era, ArrayList<Venture>> ventureList;
	
	public CardDealer() throws JsonSyntaxException, JsonIOException, FileNotFoundException{
		
		territoryList = new HashMap<Era, ArrayList<Territory>>();
		characterList = new HashMap<Era, ArrayList<Character>>();
		buildingList = new HashMap<Era, ArrayList<Building>>();
		ventureList = new HashMap<Era, ArrayList<Venture>>();
		
		for(Era e:Era.values()){
			territoryList.put(e, getTerritoryFromEra(e));
			characterList.put(e, getCharacterFromEra(e));
			buildingList.put(e, getBuildingFromEra(e));
			ventureList.put(e, getVentureFromEra(e));
		}
	}
	
	public void updateBoard(Board b, Era e){
		updateTerritoryTower(b,e);
		updateCharacterTower(b,e);
		updateBuildingTower(b,e);
		updateVentureTower(b,e);
	}
	
	private void updateBuildingTower(Board b, Era e){
		Random r = new Random();
		for(BuildingCardArea bca: b.getBuildingTower().getAreas()){
			ArrayList<Building> list = buildingList.get(e);
			int index = r.nextInt(list.size());
			bca.setBuilding(list.get(index));
			list.remove(index);
		}
	}
	
	private void updateCharacterTower(Board b, Era e){
		Random r = new Random();
		for(CharacterCardArea bca: b.getCharacterTower().getAreas()){
			ArrayList<Character> list = characterList.get(e);
			int index = r.nextInt(list.size());
			bca.setCharacter(list.get(index));
			list.remove(index);
		}
	}
	
	private void updateTerritoryTower(Board b, Era e){
		Random r = new Random();
		for(TerritoryCardArea bca: b.getTerritoryTower().getAreas()){
			ArrayList<Territory> list = territoryList.get(e);
			int index = r.nextInt(list.size());
			bca.setTerritory(list.get(index));
			list.remove(index);
		}
	}
	
	private void updateVentureTower(Board b, Era e){
		Random r = new Random();
		for(VentureCardArea bca: b.getVentureTower().getAreas()){
			ArrayList<Venture> list = ventureList.get(e);
			int index = r.nextInt(list.size());
			bca.setVenture(list.get(index));
			list.remove(index);
		}
	}
	
	public ArrayList<Territory> getTerritoryFromEra(Era e) throws JsonSyntaxException, JsonIOException, FileNotFoundException{
		 Gson gson = new GsonBuilder()
	                .registerTypeAdapter(Effect.class,
	                        new PropertyBasedInterfaceMarshal()).create();
		 
		 File dirI = new File(territoryPath  + e.name());
		 File[] filesI = dirI.listFiles();
		 
		 ArrayList<Territory> temp = new ArrayList<Territory>();
		 
		 for(File f:filesI){
				Territory aaa = gson.fromJson(new FileReader(f), Territory.class);
				temp.add(aaa);
		 }
		 return temp;
	}
	
	public ArrayList<Character> getCharacterFromEra(Era e) throws JsonSyntaxException, JsonIOException, FileNotFoundException{
		 Gson gson = new GsonBuilder()
	                .registerTypeAdapter(Effect.class,
	                        new PropertyBasedInterfaceMarshal()).create();
		 
		 File dirI = new File(characterPath  + e.name());
		 File[] filesI = dirI.listFiles();
		 
		 ArrayList<Character> temp = new ArrayList<Character>();
		 
		 for(File f:filesI){
			 Character aaa = gson.fromJson(new FileReader(f), Character.class);
				temp.add(aaa);
		 }
		 return temp;
	}
	
	public ArrayList<Building> getBuildingFromEra(Era e) throws JsonSyntaxException, JsonIOException, FileNotFoundException{
		 Gson gson = new GsonBuilder()
	                .registerTypeAdapter(Effect.class,
	                        new PropertyBasedInterfaceMarshal()).create();
		 
		 File dirI = new File(buildingPath  + e.name());
		 File[] filesI = dirI.listFiles();
		 
		 ArrayList<Building> temp = new ArrayList<Building>();
		 
		 for(File f:filesI){
			 Building aaa = gson.fromJson(new FileReader(f), Building.class);
				temp.add(aaa);
		 }
		 return temp;
	}
	
	public ArrayList<Venture> getVentureFromEra(Era e) throws JsonSyntaxException, JsonIOException, FileNotFoundException{
		 Gson gson = new GsonBuilder()
	                .registerTypeAdapter(Effect.class,
	                        new PropertyBasedInterfaceMarshal()).create();
		 
		 File dirI = new File(venturePath  + e.name());
		 File[] filesI = dirI.listFiles();
		 
		 ArrayList<Venture> temp = new ArrayList<Venture>();
		 
		 for(File f:filesI){
			 Venture aaa = gson.fromJson(new FileReader(f), Venture.class);
				temp.add(aaa);
		 }
		 return temp;
	}
}