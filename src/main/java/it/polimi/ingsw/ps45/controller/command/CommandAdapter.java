package it.polimi.ingsw.ps45.controller.command;

import it.polimi.ingsw.ps45.exceptions.BadCommandException;
import it.polimi.ingsw.ps45.exceptions.WrongCommandArgumentException;
import it.polimi.ingsw.ps45.model.actions.ProductionMode;
import it.polimi.ingsw.ps45.model.actions.CouncilPrivilege.CoinsCouncilPrivilege;
import it.polimi.ingsw.ps45.model.actions.CouncilPrivilege.CouncilPrivilege;
import it.polimi.ingsw.ps45.model.actions.CouncilPrivilege.FaithPointsCouncilPrivilege;
import it.polimi.ingsw.ps45.model.actions.CouncilPrivilege.MilitaryPoinsCouncilPrivilege;
import it.polimi.ingsw.ps45.model.actions.CouncilPrivilege.ServantsCouncilPrivilege;
import it.polimi.ingsw.ps45.model.actions.CouncilPrivilege.WoodAndStoneCouncilPrivilege;
import it.polimi.ingsw.ps45.model.area.Board;
import it.polimi.ingsw.ps45.model.area.NoCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.BuildingCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.CharacterCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.TerritoryCardArea;
import it.polimi.ingsw.ps45.model.area.cardarea.VentureCardArea;
import it.polimi.ingsw.ps45.model.cards.VentureMode;
import it.polimi.ingsw.ps45.model.player.PawnType;
/**
 * An adapted class that takes command strings as input and returns actual object in the games memory(areas, pawns etc...)
 */
public class CommandAdapter {
	private Board b;
	
	/**
 	 * Constructor
	 * @param  b the board that of the game that the player is in so it knows which areas to return.
	 */
	public CommandAdapter(Board b){
		this.b = b;
	}
	
	/**
	 * @param  mode string with the mode chosen by the player
	 * @return      the mode as an enum object
	 */
	public VentureMode getVentureMode(String mode) throws WrongCommandArgumentException{
		switch(mode.toLowerCase()){
		case "first":
			return VentureMode.FIRST;
		case "second":
			return VentureMode.SECOND;
		default: throw new WrongCommandArgumentException("Wrong Venture Mode");
		}
	}
	
	/**
	 * @param  productionMode string with the mode chosen by the player
	 * @return      the productionMode as an enum object
	 */
	public ProductionMode[] getProductionMode(String productionMode){
		ProductionMode[] pm = new ProductionMode[6];
		char[] modes = productionMode.toCharArray();
		for(int i=0;i<pm.length;i++){
			switch(modes[i]){
			case '1': pm[i] = ProductionMode.FIRST;
						break;
			case '2': pm[i] = ProductionMode.SECOND;
						break;
			default: pm[i] = ProductionMode.NONE;
						break;
			}
		}
		return pm;
		
	}
	
	/**
	 * 
	 * @param  cp string with the councilPrivilege chosen by the player
	 * @return      the councilPrivilege as an enum object
	 */
	public CouncilPrivilege getCouncilPrivilege(String cp){
		switch(cp.toLowerCase()){
		case "coins": return new CoinsCouncilPrivilege();
		case "faith": return new FaithPointsCouncilPrivilege();
		case "military": return new MilitaryPoinsCouncilPrivilege();
		case "servants": return new ServantsCouncilPrivilege();
		case "woodandstone": return new WoodAndStoneCouncilPrivilege();
		default: return new CoinsCouncilPrivilege();
		}
	}
	
	/**
	 * 
	 * @param  pawnType string with the pawn type chosen by the player
	 * @return      the pawn type as an enum object
	 */
	public PawnType getPawnType(String pawnType) throws WrongCommandArgumentException{
		
		String s = pawnType.toLowerCase();
		
		switch(s){
		case "white":
			return PawnType.WHITE;
		case "black":
			return PawnType.BLACK;
		case "orange":
			return PawnType.ORANGE;
		case "neutral":
			return PawnType.NEUTRAL;
		default: throw new WrongCommandArgumentException("Wrong Pawn Type");
		}
	}
	
	/**
	 * @throws Exception  If the area doesn't exist.
	 * @param  s string with the NoCardArea chosen by the player
	 * @return      the NoCardArea as an object on the board
	 */
	public NoCardArea getNoCardArea(String s) throws WrongCommandArgumentException{
		return b.getAreaFromString(s);
	}
	
	/**
	 * @throws Exception  If the area doesn't exist.
	 * @param  s string with the ProductionArea chosen by the player
	 * @return      the ProductionArea as an object on the board
	 */
	public NoCardArea getProductioArea(String s) throws WrongCommandArgumentException{
		return b.getProductionAreas().getAreaFromString(s);
	}
	
	/**
	 * @throws Exception  If the area doesn't exist.
	 * @param  s string with the HarvestArea chosen by the player
	 * @return      the HarvestArea as an object on the board
	 */
	public NoCardArea getHarvestArea(String s) throws WrongCommandArgumentException{
		return b.getHarvestAreas().getAreaFromString(s);
	}
	
	/**
	 * @throws Exception  If the area doesn't exist.
	 * @param  s string with the BuildingCardArea chosen by the player
	 * @return      the BuildingCardArea as an object on the board
	 */
	public BuildingCardArea getBuildingArea(String s) throws WrongCommandArgumentException{
		return b.getBuildingTower().getAreaFromString(s);
	}
	
	/**
	 * @throws Exception  If the area doesn't exist.
	 * @param  s string with the CharacterCardArea chosen by the player
	 * @return      the CharacterCardArea as an object on the board
	 */
	public CharacterCardArea getCharacterArea(String s) throws WrongCommandArgumentException{
		return b.getCharacterTower().getAreaFromString(s);
	}
	
	/**
	 * @throws Exception  If the area doesn't exist.
	 * @param  s string with the TerritoryCardArea chosen by the player
	 * @return      the TerritoryCardArea as an object on the board
	 */
	public TerritoryCardArea getTerritoryArea(String s) throws WrongCommandArgumentException{
		return b.getTerritoryTower().getAreaFromString(s);
	}
	
	/**
	 * @throws Exception  If the area doesn't exist.
	 * @param  s string with the VentureCardArea chosen by the player
	 * @return      the Venture as an object on the board
	 */
	public VentureCardArea getVentureArea(String s) throws WrongCommandArgumentException{
		return b.getVentureTower().getAreaFromString(s);
	}
}
