package it.polimi.ingsw.ps45.view.gui;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import it.polimi.ingsw.ps45.controller.command.AcceptVaticanCommand;
import it.polimi.ingsw.ps45.controller.command.ActivateLeaderCardCommand;
import it.polimi.ingsw.ps45.controller.command.AddPlayerCommand;
import it.polimi.ingsw.ps45.controller.command.AddServantsToHarvestCommand;
import it.polimi.ingsw.ps45.controller.command.AddServantsToProductionCommand;
import it.polimi.ingsw.ps45.controller.command.Command;
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

public class GUICommandParser {
	
	private JComboBox command;
	private JComboBox area;
	private JComboBox pawn;
	private JComboBox servants;
	private JTextField modes;
	
	public GUICommandParser(JComboBox command, JComboBox area, JComboBox pawn, JComboBox servants, JTextField modes){
		this.command = command;
		this.area = area;
		this.pawn = pawn;
		this.servants = servants;
		this.modes = modes;
	}
	
	public Command parse() throws Exception{
		
		String c = (String)command.getSelectedItem();

		switch(c){
		case "endturn":
			return parseEndTurn();
		case "placepawnnocard":
			return parsePlacePawnNoCard();
		case "placepawnharvest":
			return parsePlacePawnHarvest();
		case "placepawnproduction":
			return parsePlacePawnProduction();
		case "placepawnterritory":
			return parsePlacePawnTerritory();
		case "placepawncharacter":
			return parsePlacePawnCharacter();
		case "placepawnbuilding":
			return parsePlacePawnBuilding();
		case "placepawnventure":
			return parsePlacePawnVenture();
		case "nopawnterritory":
			return parseNoPawnTerritory();
		case "nopawncharacter":
			return parseNoPawnCharacter();
		case "nopawnbuilding":
			return parseNoPawnBuilding();
		case "nopawnventure":
			return parseNoPawnVenture();
		case "acceptvatican":
			return parseAcceptVatican();
		case "refusevatican":
			return parseRefuseVatican();
		case "addservantsharvest":
			return parseAddServantsHarvest();
		case "addservantsproduction":
			return parseAddServantsProduction();
		case "cp1":
			return parseCPOne();
		case "cp2":
			return parseCPTwo();
		case "cp3":
			return parseCPThree();
		case "harvest":
			return parseHarvest();
		case "production":
			return parseProduction();
		case "activateleader":
			return parseActivateLeader();
		case "useleader":
			return parseUseLeader();
		default: throw new Exception("Bad command");
		}
	}
	
	public PlacePawnNoCardCommand parsePlacePawnNoCard() throws Exception{
		return new PlacePawnNoCardCommand((String)area.getSelectedItem(), (String)pawn.getSelectedItem(), Integer.valueOf((String)servants.getSelectedItem()));
	}
	
	
	public PlacePawnHarvestCommand parsePlacePawnHarvest() throws Exception{
		return new PlacePawnHarvestCommand((String)area.getSelectedItem(), (String)pawn.getSelectedItem(), Integer.valueOf((String)servants.getSelectedItem()));
	}
	
	public PlacePawnProductionCommand parsePlacePawnProduction() throws Exception{
		return new PlacePawnProductionCommand((String)area.getSelectedItem(), (String)pawn.getSelectedItem(), Integer.valueOf((String)servants.getSelectedItem()));
	}
	
	public PlacePawnBuildingCommand parsePlacePawnBuilding() throws Exception{
		return new PlacePawnBuildingCommand((String)area.getSelectedItem(), (String)pawn.getSelectedItem(), Integer.valueOf((String)servants.getSelectedItem()));
	}
	
	public PlacePawnCharacterCommand parsePlacePawnCharacter() throws Exception{
		return new PlacePawnCharacterCommand((String)area.getSelectedItem(), (String)pawn.getSelectedItem(), Integer.valueOf((String)servants.getSelectedItem()));
	}
	
	public PlacePawnTerritoryCommand parsePlacePawnTerritory() throws Exception{
		return new PlacePawnTerritoryCommand((String)area.getSelectedItem(), (String)pawn.getSelectedItem(), Integer.valueOf((String)servants.getSelectedItem()));
	}
	
	public PlacePawnVentureCommand parsePlacePawnVenture() throws Exception{
		return new PlacePawnVentureCommand((String)area.getSelectedItem(), (String)pawn.getSelectedItem(), Integer.valueOf((String)servants.getSelectedItem()), modes.getText());
	}
	
	public NoPawnTerritoryCommand parseNoPawnTerritory() throws Exception{
		return new NoPawnTerritoryCommand((String) area.getSelectedItem(), Integer.valueOf((String)servants.getSelectedItem()));
	}
	
	public NoPawnCharacterCommand parseNoPawnCharacter() throws Exception{
		return new NoPawnCharacterCommand((String)area.getSelectedItem(), Integer.valueOf((String)servants.getSelectedItem()));
	}
	
	public NoPawnBuildingCommand parseNoPawnBuilding() throws Exception{
		return new NoPawnBuildingCommand((String)area.getSelectedItem(), Integer.valueOf((String)servants.getSelectedItem()));
	}
	
	public NoPawnVentureCommand parseNoPawnVenture() throws Exception{
		return new NoPawnVentureCommand((String)area.getSelectedItem(), Integer.valueOf((String)servants.getSelectedItem()), modes.getText());
	}
	
	public AcceptVaticanCommand parseAcceptVatican() throws Exception{
		return new AcceptVaticanCommand();
	}
	
	public RefuseVaticanCommand parseRefuseVatican() throws Exception{
		return new RefuseVaticanCommand();
	}
	
	public AddServantsToHarvestCommand parseAddServantsHarvest() throws Exception{
		return new AddServantsToHarvestCommand(Integer.valueOf((String)servants.getSelectedItem()));
	}
	
	public AddServantsToProductionCommand parseAddServantsProduction() throws Exception{
		return new AddServantsToProductionCommand(Integer.valueOf((String)servants.getSelectedItem()));
	}
	
	public ExchangeCouncilPrivilegeOneCommand parseCPOne() throws Exception{
		String[] s = councilPrivilegeParse(1);
		return new ExchangeCouncilPrivilegeOneCommand(s[0]);
	}
	
	public ExchangeCouncilPrivilegeTwoCommand parseCPTwo() throws Exception{
		String[] s = councilPrivilegeParse(2);
		return new ExchangeCouncilPrivilegeTwoCommand(s[0], s[1]);
	}
	
	public ExchangeCouncilPrivilegeThreeCommand parseCPThree() throws Exception{
		String[] s = councilPrivilegeParse(3);
		return new ExchangeCouncilPrivilegeThreeCommand(s[0], s[1], s[2]);
	}
	
	public HarvestCommand parseHarvest() throws Exception{
		return new HarvestCommand();
	}
	
	public ProductionCommand parseProduction() throws Exception{
		return new ProductionCommand(modes.getText());
	}
	
	public EndTurnCommand parseEndTurn() throws Exception{
		return new EndTurnCommand();
	}
	
	public String[] councilPrivilegeParse(int count) throws Exception{
		String cp = (String) modes.getText();
		String[] cps = cp.split("-");
		if(cps.length != count) throw new Exception("Bad councilPrivilege command");
		return cps;
	}
	
	public  ActivateLeaderCardCommand parseActivateLeader(){
		return new ActivateLeaderCardCommand(Integer.valueOf((String)area.getSelectedItem()));
	}
	public  UseLeaderCardCommand parseUseLeader(){
		return new UseLeaderCardCommand(Integer.valueOf((String)area.getSelectedItem()));
	}
}
