package it.polimi.ingsw.ps45.model.area;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.polimi.ingsw.ps45.exceptions.AreaNotAvailableException;
import it.polimi.ingsw.ps45.exceptions.WrongCommandArgumentException;
import it.polimi.ingsw.ps45.gson.PropertyBasedInterfaceMarshal;
import it.polimi.ingsw.ps45.model.area.cardarea.BuildingTower;
import it.polimi.ingsw.ps45.model.area.cardarea.CharacterTower;
import it.polimi.ingsw.ps45.model.area.cardarea.TerritoryTower;
import it.polimi.ingsw.ps45.model.area.cardarea.VentureTower;
import it.polimi.ingsw.ps45.model.effects.Effect;

/**
 * Class that holds and manages all area objects of the game.
 */
public class Board implements HasDictionary {
	
	private static final Logger LOGGER = Logger.getLogger( Board.class.getName());
	private HashMap<String, NoCardArea> dictionary;
	private ProductionAreas productionAreas;
	
	private HarvestAreas harvestAreas;
	
	private int players;
	

	
	private NoCardArea councilPalaceArea;
	
	private TerritoryTower territoryTower;
	private CharacterTower characterTower;
	private BuildingTower buildingTower;
	private VentureTower ventureTower;
	
	
	/**
 	 * Constructor
 	 * Reads all the areas from file.
 	 * @param players the number of players (because if players < 4 some areas don't need to be instantiated)
	 */
	public Board(int players){
		this.players = players;
		dictionary = new HashMap<String, NoCardArea>();
		
		
		productionAreas = new ProductionAreas(players);
		
		harvestAreas = new HarvestAreas(players);
		
		NoCardArea coinsMarketArea = loadFromFile("serialized/areas/CoinsMarketArea.json");
		NoCardArea servantsMarketArea = loadFromFile("serialized/areas/ServantsMarketArea.json");
		NoCardArea militaryAndCoinArea = loadFromFile("serialized/areas/MilitaryAndCoinsMarketArea.json");
		NoCardArea councilPrivilegeMarketArea = loadFromFile("serialized/areas/CouncilPrivilegeMarketArea.json");
		
		councilPalaceArea = loadFromFile("serialized//areas//CouncilPalaceArea.json");
		
		dictionary.put("coinsmarketarea", coinsMarketArea);
		dictionary.put("servantsmarketarea", servantsMarketArea);
		if(players >= 4)dictionary.put("militarymndcoinarea", militaryAndCoinArea);
		if(players >= 4)dictionary.put("councilprivilegemarketarea", councilPrivilegeMarketArea);
		dictionary.put("councilpalacearea", councilPalaceArea);
		
		
		territoryTower = new TerritoryTower();
		characterTower = new CharacterTower();
		buildingTower = new BuildingTower();
		ventureTower = new VentureTower();	
	}

	/**
	 * @return An object containing all production areas.
	 */
	public ProductionAreas getProductionAreas() {
		return productionAreas;
	}

	/**
	 * @return An object containing all harvest areas.
	 */
	public HarvestAreas getHarvestAreas() {
		return harvestAreas;
	}

	/**
	 * @return the CoinsMarketArea.
	 */
	public NoCardArea getCoinsMarketArea() {
		return dictionary.get("coinsmarketarea");
	}

	/**
	 * @return the servantsMarketArea.
	 */
	public NoCardArea getServantsMarketArea() {
		return dictionary.get("servantsmarketarea");
	}

	/**
	 * @return the militaryMarketArea.
	 * @throws AreaNotAvailableException 
	 */
	public NoCardArea getMilitaryAndCoinArea() throws AreaNotAvailableException {
		if(players < 4) throw new AreaNotAvailableException("Area is not available");
		return dictionary.get("militarymndcoinarea");
	}

	/**
	 * @return the councilPrivilegeMarketArea.
	 * @throws AreaNotAvailableException if the area is not available because it was covered.
	 */
	public NoCardArea getCouncilPrivilegeMarketArea() throws AreaNotAvailableException {
		if(players < 4) throw new AreaNotAvailableException("Area is not available");
		return dictionary.get("councilprivilegemarketarea");
	}

	/**
	 * @return the CouncilPalaceArea.
	 */
	public NoCardArea getCouncilPalaceArea() {
		return councilPalaceArea;
	}

	/**
	 * @return the "Tower" that holds all territories.
	 */
	public TerritoryTower getTerritoryTower() {
		return territoryTower;
	}

	/**
	 * @return the "Tower" that holds all characters.
	 */
	public CharacterTower getCharacterTower() {
		return characterTower;
	}

	/**
	 * @return the "Tower" that holds all buildings.
	 */
	public BuildingTower getBuildingTower() {
		return buildingTower;
	}

	/**
	 * @return the "Tower" that holds all ventures.
	 */
	public VentureTower getVentureTower() {
		return ventureTower;
	}
	
	/**
	 * Loads a NoCardArea from file.
	 * @param path the path of the serialized json area file.
	 */
	public static NoCardArea loadFromFile(String path){
		Gson gson = new GsonBuilder()
                .registerTypeAdapter(Effect.class,
                        new PropertyBasedInterfaceMarshal()).create();
	 
		NoCardArea c = null;
	try {
		c = gson.fromJson(new FileReader(path), NoCardArea.class);
	} catch (JsonSyntaxException e) {
		// TODO Auto-generated catch block
		LOGGER.log(Level.SEVERE, "context", e);
	} catch (JsonIOException e) {
		// TODO Auto-generated catch block
		LOGGER.log(Level.SEVERE, "context", e);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		LOGGER.log(Level.SEVERE, "context", e);
	}
	 return c;
	}

	/**
	 * @throws Exception if the an area with the given name doesn't exist
	 * @param s name of the area. Also key in the dictionary.
	 * @return A NoCardArea that correspons with the parameter string s.
	 */
	public NoCardArea getAreaFromString(String s) throws WrongCommandArgumentException {
		if(!dictionary.containsKey(s.toLowerCase())) throw new WrongCommandArgumentException("No such key");
		return dictionary.get(s.toLowerCase());
	}

	/**
	 * @return The a HashMap which stores the name of an area as key and it's instantiated object as value.
	 */
	public HashMap<String, NoCardArea> getDictionary() {
		return dictionary;
	}

	public int getPlayers() {
		return players;
	}
	
	


	
	
	
	

}
