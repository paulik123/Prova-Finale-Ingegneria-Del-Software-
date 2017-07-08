package it.polimi.ingsw.ps45.model.player;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.polimi.ingsw.ps45.gson.GsonWithInterface;
import it.polimi.ingsw.ps45.model.cards.Building;
import it.polimi.ingsw.ps45.model.cards.Character;
import it.polimi.ingsw.ps45.model.cards.LeaderCard;
import it.polimi.ingsw.ps45.model.cards.Territory;
import it.polimi.ingsw.ps45.model.cards.Venture;
import it.polimi.ingsw.ps45.model.game.Game;

/**
 * A set that contains all player's cards, bonus tile, consumables, action value modifiers and permanent effects.
 */
public class ResourceSet {
	
	private static final Logger LOGGER = Logger.getLogger( ResourceSet.class.getName());
	
	private ConsumableSet resources;
	
	private ConsumableSet territoryActionDiscount;
	private ConsumableSet characterActionDiscount;
	private ConsumableSet buildingActionDiscount;
	private ConsumableSet ventureActionDiscount;
	
	private ConsumableSet collectPenalty;
	
	private PawnValueModifier pawnValueModifier;
	
	private ActionValueModifier actionValueModifier;
	
	private PermanentEffects permanentEffects;
	
	private PersonalBonusTile bonusTile;
	


	private PawnSet pawnSet;
	
	private ArrayList<Territory> territoryList;
	private ArrayList<Building> buildingList;
	private ArrayList<Character> characterList;
	private ArrayList<Venture> ventureList;
	private ArrayList<LeaderCard> leaderCardList;
	private ArrayList<LeaderCard> activatedLeaderCardList;
	
	
	
	/**
 	 * Constructor
	 * @param initialResources The initial resources the player will have. Also decided by the game depending on the number of players.
	 * @param bonusTile the id of the bonusTile the player has chosen it order for it to be initialized.
	 * @param leaderCards a list of leader cards the player will own and can activate when he meets the requirements.
	 */
	public ResourceSet(ConsumableSet initialResources, String bonusTile, ArrayList<LeaderCard> leaderCards){
		this.resources = initialResources;
		
		territoryList = new ArrayList<Territory>();
		buildingList = new ArrayList<Building>();
		characterList = new ArrayList<Character>();
		ventureList = new ArrayList<Venture>();
		leaderCardList = leaderCards;
		activatedLeaderCardList = new ArrayList<LeaderCard>();
		
		this.territoryActionDiscount = new ConsumableSet();
		this.characterActionDiscount = new ConsumableSet();
		this.buildingActionDiscount = new ConsumableSet();
		this.ventureActionDiscount = new ConsumableSet();
		
		this.pawnSet = new PawnSet();
		
		this.collectPenalty = new ConsumableSet();
		
		this.pawnValueModifier = new PawnValueModifier();
		
		this.actionValueModifier = new ActionValueModifier();
		
		this.permanentEffects = new PermanentEffects();
		
		initializeBonusTile(bonusTile);
		
	}
	
	/**
	 * Makes the player collect another ConsumableSet. Any discounts or penalties are applied before the set is collected.
	 * @param cs the set the will be collected.
	 */
	public void collect(ConsumableSet cs){
		cs.makeDiscount(collectPenalty);
		resources.collect(cs);
	}
	
	
	/**
	 * Makes the player pay a consumableSet and a Pawn.
	 * @param c the set that contains the resources that will be subtracted from the player's resources.
	 * @param pawn the pawn that will be used. It will be set to unavailable.
	 */
	public void pay(ConsumableSet c, Pawn pawn){
			resources.pay(c);
			pawn.setAvailable(false);
			pawn.setValue(0);
	}	
	
	/**
	 * @param cSet the ConsumableSet the player's resources will be compared with.
	 * @return true the player has enough consumables when compared to the given set.
	 */
	public boolean hasConsumables(ConsumableSet cSet){
		return resources.hasConsumablesAvailable(cSet);
	}
	
	
	/**
	 * Sets the new value of a pawn in the set.
	 * @param type the type of the pawn in the set.
	 * @param value the new value of the pawn.
	 * @param available the new availability of the pawn.
	 */
	public void setPawn(PawnType pt, int value, boolean b){
		if(permanentEffects.isValueFivePawn()){
			pawnSet.set(pt, 5, b);
			return;
		}
		pawnSet.set(pt, value, b);
	}
	
	/**
	 * @param pt the pawn type of the returned pawn.
	 * @return the player's pawn corresponding to the given pawn type.
	 */
	public Pawn getPawn(PawnType type){
		return pawnSet.get(type);
	}

	
	/**
	 * @param pt the pawn type of the player's pawn that you want to know the value.
	 * @return the pawn's value + any existing value modifiers.
	 */
	public int getPawnValue(PawnType pt){
		return pawnSet.get(pt).getValue() + pawnValueModifier.getValue(pt);
	}
	
	/**
	 * @param pt the pawn type you want to get the modifier for.
	 * @return the pawn type's value modifier.
	 */
	public int getPawnModifier(PawnType pt){
		return pawnValueModifier.getValue(pt);
	}
	
	/**
	 * Sets the new value of a pawn in the set.
	 * @param pt the type of the pawn in the set.
	 * @param value the new value of the modifier.
	 */
	public void setModifierPawn(PawnType pt, int value){
		pawnValueModifier.setValue(pt, value);
	}
	
	/**
	 * @return a ConsumableSet representing to the player's territory action discount.
	 */
	public ConsumableSet getTerritoryActionDiscount(){
		return territoryActionDiscount;
	}
	
	/**
	 * @return a ConsumableSet representing to the player's character action discount.
	 */
	public ConsumableSet getCharacterActionDiscount(){
		return characterActionDiscount;
	}
	
	/**
	 * @return a ConsumableSet representing to the player's building action discount.
	 */
	public ConsumableSet getBuildingActionDiscount(){
		return buildingActionDiscount;
	}
	
	/**
	 * @return a ConsumableSet representing to the player's venture action discount.
	 */
	public ConsumableSet getVentureActionDiscount(){
		return ventureActionDiscount;
	}
	
	/**
	 * @return a list with all the territories the player owns.
	 */
	public ArrayList<Territory> getTerritoryList() {
		return territoryList;
	}

	/**
	 * @return a list with all the buildings the player owns.
	 */
	public ArrayList<Building> getBuildingList() {
		return buildingList;
	}


	/**
	 * @return a list with all the characters the player owns.
	 */
	public ArrayList<Character> getCharacterList() {
		return characterList;
	}

	/**
	 * @return a list with all the ventures the player owns.
	 */
	public ArrayList<Venture> getVentureList() {
		return ventureList;
	}
	
	/**
	 * @return the set of all the player's permanent effects.
	 */
	public PermanentEffects getPermanentEffects() {
		return permanentEffects;
	}


	/**
	 * @return a ConsumableSet representing the players resources (wood/stone/coins/servants/etc...).
	 */
	public ConsumableSet getResources() {
		return resources;
	}


	/**
	 * @return the player's Bonus Tile.
	 */
	public PersonalBonusTile getBonusTile() {
		return bonusTile;
	}


	/**
	 * @return a ConsumableSet representing the player's penalties when collecting resources.
	 */
	public ConsumableSet getCollectPenalty() {
		return collectPenalty;
	}


	/**
	 * @return an object representing the players action value modifiers.
	 */
	public ActionValueModifier getActionValueModifier() {
		return actionValueModifier;
	}


	/**
	 * @return the set which contains all the pawns the player currently owns.
	 */
	public PawnSet getPawnSet() {
		return pawnSet;
	}
	
	/**
	 * Initializes the players Bonus Tile from file.
	 * @param bt the Bonus tile's id.
	 */
	public void initializeBonusTile(String bt){
		Gson gson = GsonWithInterface.getGson();
		
		
		try {
			bonusTile = gson.fromJson(new FileReader("serialized/bonustiles/" + bt  + ".json"), PersonalBonusTile.class);
		} catch (Exception e) {
			try {
				bonusTile = gson.fromJson(new FileReader("serialized/bonustiles/0.json"), PersonalBonusTile.class);
			} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e1) {
				LOGGER.log(Level.SEVERE, "context", e);
			}
		}
	}
	
	/**
	 * Removes one of the player's leader card from the unactivated list and moves it to the activated leader card list.
	 * @param index the index of the card in the unactivated leader card list. 
	 */
	public void activateLeaderCard(int index){
		
		activatedLeaderCardList.add(leaderCardList.get(index));
		leaderCardList.remove(index);
	}


	/**
	 * @return a list with all the unactivated leader cards the player currently owns.
	 */
	public ArrayList<LeaderCard> getLeaderCardList() {
		return leaderCardList;
	}


	/**
	 * @return a list with all the activated leader cards the player currently owns.
	 */
	public ArrayList<LeaderCard> getActivatedLeaderCardList() {
		return activatedLeaderCardList;
	}
	
	/**
	 * Sets the value of that player's smallest pawn to six if the player suffers from that effect.
	 */
	public void setSmallestToPawnSix(){
		if(!permanentEffects.isSmallestPawnHasSixValue()) return;
		int black = pawnSet.get(PawnType.BLACK).getValue();
		int white = pawnSet.get(PawnType.WHITE).getValue();
		int orange = pawnSet.get(PawnType.ORANGE).getValue();
		
		if(black <= white && black <= orange)pawnSet.set(PawnType.BLACK, 6, true);
		else if (white <= black && white <= orange) pawnSet.set(PawnType.WHITE, 6, true);
		else pawnSet.set(PawnType.ORANGE, 6, true);
	}
	
	
	
	

}


