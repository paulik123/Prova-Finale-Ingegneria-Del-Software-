package it.polimi.ingsw.ps45.client;

import it.polimi.ingsw.ps45.controller.command.AcceptVaticanCommand;
import it.polimi.ingsw.ps45.controller.command.ActivateLeaderCardCommand;
import it.polimi.ingsw.ps45.controller.command.AddServantsToHarvestCommand;
import it.polimi.ingsw.ps45.controller.command.AddServantsToProductionCommand;
import it.polimi.ingsw.ps45.controller.command.Command;
import it.polimi.ingsw.ps45.controller.command.DiscardLeaderCard;
import it.polimi.ingsw.ps45.controller.command.EndTurnCommand;
import it.polimi.ingsw.ps45.controller.command.ExchangeCouncilPrivilegeOneCommand;
import it.polimi.ingsw.ps45.controller.command.ExchangeCouncilPrivilegeThreeCommand;
import it.polimi.ingsw.ps45.controller.command.ExchangeCouncilPrivilegeTwoCommand;
import it.polimi.ingsw.ps45.controller.command.HarvestCommand;
import it.polimi.ingsw.ps45.controller.command.NoPawnBuildingCommand;
import it.polimi.ingsw.ps45.controller.command.NoPawnCharacterCommand;
import it.polimi.ingsw.ps45.controller.command.NoPawnTerritoryCommand;
import it.polimi.ingsw.ps45.controller.command.NoPawnVentureCommand;
import it.polimi.ingsw.ps45.controller.command.PlacePawnBuildingCommand;
import it.polimi.ingsw.ps45.controller.command.PlacePawnCharacterCommand;
import it.polimi.ingsw.ps45.controller.command.PlacePawnHarvestCommand;
import it.polimi.ingsw.ps45.controller.command.PlacePawnNoCardCommand;
import it.polimi.ingsw.ps45.controller.command.PlacePawnProductionCommand;
import it.polimi.ingsw.ps45.controller.command.PlacePawnTerritoryCommand;
import it.polimi.ingsw.ps45.controller.command.PlacePawnVentureCommand;
import it.polimi.ingsw.ps45.controller.command.ProductionCommand;
import it.polimi.ingsw.ps45.controller.command.RefuseVaticanCommand;
import it.polimi.ingsw.ps45.controller.command.UseLeaderCardCommand;
import it.polimi.ingsw.ps45.exceptions.BadCommandException;


/**
 * Adapter class that takes string that takes strings as input and outputs commands. 
 * Used to make the "translation" between user input and actual command objects.
 */
public class CommandParser {

	/**
	 * Just a big switch which contains cases for all the commands.
	 * Also split the input string.
	 * @throws BadCommandException if the user input is wrong or badly formatted.
	 * @return the parsed Command.
	 */
	public Command parse(String input) throws BadCommandException{
		String[] s = input.toLowerCase().split("-");
		
		switch(s[0]){
		case "endturn":
			return parseEndTurn(s);
		case "placepawnnocard":
			return parsePlacePawnNoCard(s);
		case "placepawnharvest":
			return parsePlacePawnHarvest(s);
		case "placepawnproduction":
			return parsePlacePawnProduction(s);
		case "placepawnterritory":
			return parsePlacePawnTerritory(s);
		case "placepawncharacter":
			return parsePlacePawnCharacter(s);
		case "placepawnbuilding":
			return parsePlacePawnBuilding(s);
		case "placepawnventure":
			return parsePlacePawnVenture(s);
		case "nopawnterritory":
			return parseNoPawnTerritory(s);
		case "nopawncharacter":
			return parseNoPawnCharacter(s);
		case "nopawnbuilding":
			return parseNoPawnBuilding(s);
		case "nopawnventure":
			return parseNoPawnVenture(s);
		case "acceptvatican":
			return parseAcceptVatican(s);
		case "refusevatican":
			return parseRefuseVatican(s);
		case "addservantsharvest":
			return parseAddServantsHarvest(s);
		case "addservantsproduction":
			return parseAddServantsProduction(s);
		case "cp1":
			return parseCPOne(s);
		case "cp2":
			return parseCPTwo(s);
		case "cp3":
			return parseCPThree(s);
		case "harvest":
			return parseHarvest(s);
		case "production":
			return parseProduction(s);
		case "activateleader":
			return parseActivateLeader(s);
		case "useleader":
			return parseUseLeader(s);
		case "discardleader":
			return parseDiscardLeader(s);
		default: throw new BadCommandException("Bad command");
		}
	}
	
	/**
	 * s[1] = area
	 * s[2] = pawn type
	 * s[3] = servants added
	 * @return the parsed command.
	 */
	public PlacePawnNoCardCommand parsePlacePawnNoCard(String[] s) throws BadCommandException{
		if(s.length != 4) throw new BadCommandException("Bad command");
		return new PlacePawnNoCardCommand(s[1], s[2], Integer.valueOf(s[3]));
	}
	
	/**
	 * s[1] = area
	 * s[2] = pawn type
	 * s[3] = servants added
	 * @return the parsed command.
	 */
	public PlacePawnHarvestCommand parsePlacePawnHarvest(String[] s) throws BadCommandException{
		if(s.length != 4) throw new BadCommandException("Bad command");
		return new PlacePawnHarvestCommand(s[1], s[2], Integer.valueOf(s[3]));
	}
	
	/**
	 * s[1] = area
	 * s[2] = pawn type
	 * s[3] = servants added
	 * @return the parsed command.
	 */
	public PlacePawnProductionCommand parsePlacePawnProduction(String[] s) throws BadCommandException{
		if(s.length != 4) throw new BadCommandException("Bad command");
		return new PlacePawnProductionCommand(s[1], s[2], Integer.valueOf(s[3]));
	}
	
	/**
	 * s[1] = area
	 * s[2] = pawn type
	 * s[3] = servants added
	 * @return the parsed command.
	 */
	public PlacePawnBuildingCommand parsePlacePawnBuilding(String[] s) throws BadCommandException{
		if(s.length != 4) throw new BadCommandException("Bad command");
		return new PlacePawnBuildingCommand(s[1], s[2], Integer.valueOf(s[3]));
	}
	
	/**
	 * s[1] = area
	 * s[2] = pawn type
	 * s[3] = servants added
	 * @return the parsed command.
	 */
	public PlacePawnCharacterCommand parsePlacePawnCharacter(String[] s) throws BadCommandException{
		if(s.length != 4) throw new BadCommandException("Bad command");
		return new PlacePawnCharacterCommand(s[1], s[2], Integer.valueOf(s[3]));
	}
	
	/**
	 * s[1] = area
	 * s[2] = pawn type
	 * s[3] = servants added
	 * @return the parsed command.
	 */
	public PlacePawnTerritoryCommand parsePlacePawnTerritory(String[] s) throws BadCommandException{
		if(s.length != 4) throw new BadCommandException("Bad command");
		return new PlacePawnTerritoryCommand(s[1], s[2], Integer.valueOf(s[3]));
	}
	
	/**
	 * s[1] = area
	 * s[2] = pawn type
	 * s[3] = servants added
	 * @return the parsed command.
	 */
	public PlacePawnVentureCommand parsePlacePawnVenture(String[] s) throws BadCommandException{
		if(s.length != 5) throw new BadCommandException("Bad command");
		return new PlacePawnVentureCommand(s[1], s[2], Integer.valueOf(s[3]), s[4]);
	}
	
	/**
	 * s[1] = area
	 * s[2] = servants added
	 * @return the parsed command.
	 */
	public NoPawnTerritoryCommand parseNoPawnTerritory(String[] s) throws BadCommandException{
		if(s.length != 3) throw new BadCommandException("Bad command");
		return new NoPawnTerritoryCommand(s[1], Integer.valueOf(s[2]));
	}
	
	/**
	 * s[1] = area
	 * s[2] = servants added
	 * @return the parsed command.
	 */
	public NoPawnCharacterCommand parseNoPawnCharacter(String[] s) throws BadCommandException{
		if(s.length != 3) throw new BadCommandException("Bad command");
		return new NoPawnCharacterCommand(s[1], Integer.valueOf(s[2]));
	}
	
	/**
	 * s[1] = area
	 * s[2] = servants added
	 * @return the parsed command.
	 */
	public NoPawnBuildingCommand parseNoPawnBuilding(String[] s) throws BadCommandException{
		if(s.length != 3) throw new BadCommandException("Bad command");
		return new NoPawnBuildingCommand(s[1], Integer.valueOf(s[2]));
	}
	
	/**
	 * s[1] = area
	 * s[2] = servants added
	 * @return the parsed command.
	 */
	public NoPawnVentureCommand parseNoPawnVenture(String[] s) throws BadCommandException{
		if(s.length != 4) throw new BadCommandException("Bad command");
		return new NoPawnVentureCommand(s[1], Integer.valueOf(s[2]), s[3]);
	}
	
	/**
	 * s[1] = area
	 * s[2] = servants added
	 * @return the parsed command.
	 */
	public AcceptVaticanCommand parseAcceptVatican(String[] s) throws BadCommandException{
		if(s.length != 1) throw new BadCommandException("Bad command");
		return new AcceptVaticanCommand();
	}
	
	/**
	 * @return the parsed command.
	 */
	public RefuseVaticanCommand parseRefuseVatican(String[] s) throws BadCommandException{
		if(s.length != 1) throw new BadCommandException("Bad command");
		return new RefuseVaticanCommand();
	}
	
	/**
	 * s[1] = servants added
	 * @return the parsed command.
	 */
	public AddServantsToHarvestCommand parseAddServantsHarvest(String[] s) throws BadCommandException{
		if(s.length != 2) throw new BadCommandException("Bad command");
		return new AddServantsToHarvestCommand(Integer.valueOf(s[1]));
	}
	
	/**
	 * s[1] = servants added
	 * @return the parsed command.
	 */
	public AddServantsToProductionCommand parseAddServantsProduction(String[] s) throws BadCommandException{
		if(s.length != 2) throw new BadCommandException("Bad command");
		return new AddServantsToProductionCommand(Integer.valueOf(s[1]));
	}
	
	/**
	 * s[1] = the council privilege type
	 * @return the parsed command.
	 */
	public ExchangeCouncilPrivilegeOneCommand parseCPOne(String[] s) throws BadCommandException{
		if(s.length != 2) throw new BadCommandException("Bad command");
		return new ExchangeCouncilPrivilegeOneCommand(s[1]);
	}
	
	/**
	 * s[1] = the first council privilege type
	 * s[2] = the second council privilege type
	 * @return the parsed command.
	 */
	public ExchangeCouncilPrivilegeTwoCommand parseCPTwo(String[] s) throws BadCommandException{
		if(s.length != 3) throw new BadCommandException("Bad command");
		return new ExchangeCouncilPrivilegeTwoCommand(s[1], s[2]);
	}
	
	/**
	 * s[1] = the first council privilege type
	 * s[2] = the second council privilege type
	 * s[3] = the third council privilege type
	 * @return the parsed command.
	 */
	public ExchangeCouncilPrivilegeThreeCommand parseCPThree(String[] s) throws BadCommandException{
		if(s.length != 4) throw new BadCommandException("Bad command");
		return new ExchangeCouncilPrivilegeThreeCommand(s[1], s[2], s[3]);
	}
	
	/**
	 * @return the parsed command.
	 */
	public HarvestCommand parseHarvest(String[] s) throws BadCommandException{
		if(s.length != 1) throw new BadCommandException("Bad command");
		return new HarvestCommand();
	}
	
	/**
	 * s[1] = production modes requested by the player.
	 * @return the parsed command.
	 */
	public ProductionCommand parseProduction(String[] s) throws BadCommandException{
		if(s.length != 2) throw new BadCommandException("Bad command");
		return new ProductionCommand(s[1]);
	}
	
	/**
	 * @return the parsed command.
	 */
	public EndTurnCommand parseEndTurn(String[] s) throws BadCommandException{
		if(s.length != 1) throw new BadCommandException("Bad command");
		return new EndTurnCommand();
	}
	
	/**
	 * s[1] = the index of the leader card in the player's leader card list.
	 * @return the parsed command.
	 */
	public  ActivateLeaderCardCommand parseActivateLeader(String[] s){
		return new ActivateLeaderCardCommand(Integer.valueOf(s[1]));
	}
	
	/**
	 * s[1] = the index of the leader card in the player's activated leader card list.
	 * @return the parsed command.
	 */
	public  UseLeaderCardCommand parseUseLeader(String[] s){
		return new UseLeaderCardCommand(Integer.valueOf(s[1]));
	}
	
	/**
	 * s[1] = the index of the leader card in the player's activated leader card list.
	 * @return the parsed command.
	 */
	public DiscardLeaderCard parseDiscardLeader(String[] s){
		return new DiscardLeaderCard(Integer.valueOf(s[1]), s[2]);
	}
	
}
