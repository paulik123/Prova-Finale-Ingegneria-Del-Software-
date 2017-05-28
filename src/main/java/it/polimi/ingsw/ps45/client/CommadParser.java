package it.polimi.ingsw.ps45.client;

import it.polimi.ingsw.ps45.controller.command.AcceptVaticanCommand;
import it.polimi.ingsw.ps45.controller.command.AddPlayerCommand;
import it.polimi.ingsw.ps45.controller.command.AddServantsToHarvestCommand;
import it.polimi.ingsw.ps45.controller.command.AddServantsToProductionCommand;
import it.polimi.ingsw.ps45.controller.command.Command;
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
import it.polimi.ingsw.ps45.controller.command.PlacePawnMarketCommand;
import it.polimi.ingsw.ps45.controller.command.PlacePawnNoCardCommand;
import it.polimi.ingsw.ps45.controller.command.PlacePawnProductionCommand;
import it.polimi.ingsw.ps45.controller.command.PlacePawnTerritoryCommand;
import it.polimi.ingsw.ps45.controller.command.PlacePawnVentureCommand;
import it.polimi.ingsw.ps45.controller.command.ProductionCommand;
import it.polimi.ingsw.ps45.controller.command.RefuseVaticanCommand;

public class CommadParser {
	public Command parse(String input) throws Exception{
		String[] s = input.toLowerCase().split("-");
		
		switch(s[0]){
		case "placepawnnocard":
			return parsePlacePawnNoCard(s);
		case "placepawnmarket":
			return parsePlacePawnMarket(s);
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
		case "joingame":
			return parseJoinGame(s);
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
		default: throw new Exception("Bad command");
		}
	}
	public PlacePawnNoCardCommand parsePlacePawnNoCard(String[] s) throws Exception{
		if(s.length != 4) throw new Exception("Bad command");
		return new PlacePawnNoCardCommand(s[1], s[2], Integer.valueOf(s[3]));
	}
	
	public PlacePawnMarketCommand parsePlacePawnMarket(String[] s) throws Exception{
		if(s.length != 4) throw new Exception("Bad command");
		return new PlacePawnMarketCommand(s[1], s[2], Integer.valueOf(s[3]));
	}
	
	public PlacePawnHarvestCommand parsePlacePawnHarvest(String[] s) throws Exception{
		if(s.length != 4) throw new Exception("Bad command");
		return new PlacePawnHarvestCommand(s[1], s[2], Integer.valueOf(s[3]));
	}
	
	public PlacePawnProductionCommand parsePlacePawnProduction(String[] s) throws Exception{
		if(s.length != 4) throw new Exception("Bad command");
		return new PlacePawnProductionCommand(s[1], s[2], Integer.valueOf(s[3]));
	}
	
	public PlacePawnBuildingCommand parsePlacePawnBuilding(String[] s) throws Exception{
		if(s.length != 4) throw new Exception("Bad command");
		return new PlacePawnBuildingCommand(s[1], s[2], Integer.valueOf(s[3]));
	}
	
	public PlacePawnCharacterCommand parsePlacePawnCharacter(String[] s) throws Exception{
		if(s.length != 4) throw new Exception("Bad command");
		return new PlacePawnCharacterCommand(s[1], s[2], Integer.valueOf(s[3]));
	}
	
	public PlacePawnTerritoryCommand parsePlacePawnTerritory(String[] s) throws Exception{
		if(s.length != 4) throw new Exception("Bad command");
		return new PlacePawnTerritoryCommand(s[1], s[2], Integer.valueOf(s[3]));
	}
	
	public PlacePawnVentureCommand parsePlacePawnVenture(String[] s) throws Exception{
		if(s.length != 5) throw new Exception("Bad command");
		return new PlacePawnVentureCommand(s[1], s[2], Integer.valueOf(s[3]), s[4]);
	}
	
	public NoPawnTerritoryCommand parseNoPawnTerritory(String[] s) throws Exception{
		if(s.length != 3) throw new Exception("Bad command");
		return new NoPawnTerritoryCommand(s[1], Integer.valueOf(s[2]));
	}
	
	public NoPawnCharacterCommand parseNoPawnCharacter(String[] s) throws Exception{
		if(s.length != 3) throw new Exception("Bad command");
		return new NoPawnCharacterCommand(s[1], Integer.valueOf(s[2]));
	}
	
	public NoPawnBuildingCommand parseNoPawnBuilding(String[] s) throws Exception{
		if(s.length != 3) throw new Exception("Bad command");
		return new NoPawnBuildingCommand(s[1], Integer.valueOf(s[2]));
	}
	
	public NoPawnVentureCommand parseNoPawnVenture(String[] s) throws Exception{
		if(s.length != 4) throw new Exception("Bad command");
		return new NoPawnVentureCommand(s[1], Integer.valueOf(s[2]), s[3]);
	}
	
	public AddPlayerCommand parseJoinGame(String[] s) throws Exception{
		if(s.length != 1) throw new Exception("Bad command");
		return new AddPlayerCommand();
	}
	
	public AcceptVaticanCommand parseAcceptVatican(String[] s) throws Exception{
		if(s.length != 1) throw new Exception("Bad command");
		return new AcceptVaticanCommand();
	}
	
	public RefuseVaticanCommand parseRefuseVatican(String[] s) throws Exception{
		if(s.length != 1) throw new Exception("Bad command");
		return new RefuseVaticanCommand();
	}
	
	public AddServantsToHarvestCommand parseAddServantsHarvest(String[] s) throws Exception{
		if(s.length != 2) throw new Exception("Bad command");
		return new AddServantsToHarvestCommand(Integer.valueOf(s[1]));
	}
	
	public AddServantsToProductionCommand parseAddServantsProduction(String[] s) throws Exception{
		if(s.length != 2) throw new Exception("Bad command");
		return new AddServantsToProductionCommand(Integer.valueOf(s[1]));
	}
	
	public ExchangeCouncilPrivilegeOneCommand parseCPOne(String[] s) throws Exception{
		if(s.length != 2) throw new Exception("Bad command");
		return new ExchangeCouncilPrivilegeOneCommand(s[1]);
	}
	
	public ExchangeCouncilPrivilegeTwoCommand parseCPTwo(String[] s) throws Exception{
		if(s.length != 3) throw new Exception("Bad command");
		return new ExchangeCouncilPrivilegeTwoCommand(s[1], s[2]);
	}
	
	public ExchangeCouncilPrivilegeThreeCommand parseCPThree(String[] s) throws Exception{
		if(s.length != 4) throw new Exception("Bad command");
		return new ExchangeCouncilPrivilegeThreeCommand(s[1], s[2], s[3]);
	}
	
	public HarvestCommand parseHarvest(String[] s) throws Exception{
		if(s.length != 1) throw new Exception("Bad command");
		return new HarvestCommand();
	}
	
	public ProductionCommand parseProduction(String[] s) throws Exception{
		if(s.length != 2) throw new Exception("Bad command");
		return new ProductionCommand(s[1]);
	}
	
}
