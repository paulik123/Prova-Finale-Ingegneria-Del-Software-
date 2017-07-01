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

import it.polimi.ingsw.ps45.gson.GsonWithInterface;
import it.polimi.ingsw.ps45.gson.PropertyBasedInterfaceMarshal;
import it.polimi.ingsw.ps45.model.area.Board;
import it.polimi.ingsw.ps45.model.area.cardarea.BuildingCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.CharacterCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.TerritoryCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.VentureCardArea;
import it.polimi.ingsw.ps45.model.effects.Effect;


/**
 * Class that reads all the cards from file using GSON then "deals" them as requested by the game.
 */
public class CardDealer {
	
	private static final String territoryPath = "serialized//cards//territories//";
	private static final String characterPath = "serialized//cards//characters//";
	private static final String buildingPath = "serialized//cards//buildings//";
	private static final String venturePath = "serialized//cards//ventures//";
	private static final String leaderPath = "serialized//cards//leader//";
	
	
	
	private HashMap<Era, ArrayList<Territory>> territoryList;
	private HashMap<Era, ArrayList<Character>> characterList;
	private HashMap<Era, ArrayList<Building>> buildingList;
	private HashMap<Era, ArrayList<Venture>> ventureList;
	private ArrayList<LeaderCard> leaderList;
	
	
	/**
 	 * Constructor
 	 * Reads all the cards from file
 	 * @throws JsonSyntaxException if the serialized json card is not well formatted.
 	 * @throws JsonIOException if Gson can't read the serialized files.
 	 * @throws FileNotFoundException if the constructor doesn't find a serialized card.
	 */
	public CardDealer() throws JsonSyntaxException, JsonIOException, FileNotFoundException{
		
		territoryList = new HashMap<Era, ArrayList<Territory>>();
		characterList = new HashMap<Era, ArrayList<Character>>();
		buildingList = new HashMap<Era, ArrayList<Building>>();
		ventureList = new HashMap<Era, ArrayList<Venture>>();
		leaderList = getLeaders();
		
		for(Era e:Era.values()){
			territoryList.put(e, getTerritoryFromEra(e));
			characterList.put(e, getCharacterFromEra(e));
			buildingList.put(e, getBuildingFromEra(e));
			ventureList.put(e, getVentureFromEra(e));
		}

	}
	
	/**
	 * 
	 * Updates a board with fresh, unused cards.
	 * @param b The board to be updated.
	 * @param  e Era from which the cards should belong to.
	 */
	public void updateBoard(Board b, Era e){
		updateTerritoryTower(b,e);
		updateCharacterTower(b,e);
		updateBuildingTower(b,e);
		updateVentureTower(b,e);
	}
	
	/**
	 * @return a list with four Leader Cards needed when adding a new player(every player has to have 4 leader cards). The cards are chosen at random.
	 */
	public  ArrayList<LeaderCard> getFourLeaders(){
		Random r = new Random();
		ArrayList<LeaderCard> list = new ArrayList<LeaderCard>();
		
		for(int i = 0; i < 4; i++){
			int index = r.nextInt(leaderList.size());
			list.add(leaderList.get(index));
			leaderList.remove(index);
		}
		return list;
	}
	
	/**
	 * 
	 * Updates a BuildingTower with fresh, unused cards.
	 * The cards are chosen at random.
	 * @param b The board which contains the building tower that will be updated.
	 * @param e Era from which the cards should belong to.
	 */
	private void updateBuildingTower(Board b, Era e){
		Random r = new Random();
		for(BuildingCardArea bca: b.getBuildingTower().getAreas()){
			ArrayList<Building> list = buildingList.get(e);
			int index = r.nextInt(list.size());
			bca.setBuilding(list.get(index));
			list.remove(index);
		}
	}
	
	/**
	 * 
	 * Updates a CharacterTower with fresh, unused cards.
	 * The cards are chosen at random.
	 * @param b The board which contains the character tower that will be updated.
	 * @param e Era from which the cards should belong to.
	 */
	private void updateCharacterTower(Board b, Era e){
		Random r = new Random();
		for(CharacterCardArea bca: b.getCharacterTower().getAreas()){
			ArrayList<Character> list = characterList.get(e);
			int index = r.nextInt(list.size());
			bca.setCharacter(list.get(index));
			list.remove(index);
		}
	}
	
	/**
	 * 
	 * Updates a TerritoryTower with fresh, unused cards.
	 * The cards are chosen at random.
	 * @param b The board which contains the territory tower that will be updated.
	 * @param e Era from which the cards should belong to.
	 */
	private void updateTerritoryTower(Board b, Era e){
		Random r = new Random();
		for(TerritoryCardArea bca: b.getTerritoryTower().getAreas()){
			ArrayList<Territory> list = territoryList.get(e);
			int index = r.nextInt(list.size());
			bca.setTerritory(list.get(index));
			list.remove(index);
		}
	}
	
	/**
	 * 
	 * Updates a VentureTower with fresh, unused cards.
	 * The cards are chosen at random.
	 * @param b The board which contains the venture tower that will be updated.
	 * @param e Era from which the cards should belong to.
	 */
	private void updateVentureTower(Board b, Era e){
		Random r = new Random();
		for(VentureCardArea bca: b.getVentureTower().getAreas()){
			ArrayList<Venture> list = ventureList.get(e);
			int index = r.nextInt(list.size());
			bca.setVenture(list.get(index));
			list.remove(index);
		}
	}
	
	/**
	 * 
	 * @param e Era from which the cards should belong to.
	 * @return a list with all territories of a given era.
	 * @throws JsonSyntaxException if the serialized json card is not well formatted.
 	 * @throws JsonIOException if Gson can't read the serialized files.
 	 * @throws FileNotFoundException if the constructor doesn't find a serialized card.
	 */
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
	
	/**
	 * 
	 * @param e Era from which the cards should belong to.
	 * @return a list with all characters of a given era.
	 * @throws JsonSyntaxException if the serialized json card is not well formatted.
 	 * @throws JsonIOException if Gson can't read the serialized files.
 	 * @throws FileNotFoundException if the constructor doesn't find a serialized card.
	 */
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
	
	/**
	 * 
	 * @param e Era from which the cards should belong to.
	 * @return a list with all buildings of a given era.
	 * @throws JsonSyntaxException if the serialized json card is not well formatted.
 	 * @throws JsonIOException if Gson can't read the serialized files.
 	 * @throws FileNotFoundException if the constructor doesn't find a serialized card.
	 */
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
	
	/**
	 * 
	 * @param e Era from which the cards should belong to.
	 * @return a list with all ventures of a given era.
	 * @throws JsonSyntaxException if the serialized json card is not well formatted.
 	 * @throws JsonIOException if Gson can't read the serialized files.
 	 * @throws FileNotFoundException if the constructor doesn't find a serialized card.
	 */
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
	
	/**
	 * 
	 * @param e Era from which the cards should belong to.
	 * @return a list with all leader cards of a given era.
	 * @throws JsonSyntaxException if the serialized json card is not well formatted.
 	 * @throws JsonIOException if Gson can't read the serialized files.
 	 * @throws FileNotFoundException if the constructor doesn't find a serialized card.
	 */
	public ArrayList<LeaderCard> getLeaders() throws JsonSyntaxException, JsonIOException, FileNotFoundException{
		 Gson gson = GsonWithInterface.getGson();
		 
		 File dirI = new File(leaderPath);
		 File[] filesI = dirI.listFiles();
		 
		 ArrayList<LeaderCard> temp = new ArrayList<LeaderCard>();
		 
		 for(File f:filesI){
			 LeaderCard aaa = gson.fromJson(new FileReader(f), LeaderCard.class);
				temp.add(aaa);
		 }
		 return temp;
	}
}
